$(function (){
    $("#btn_toggle").click(function (){
        $("#toggle").toggle();
    });
});

async function fetchDetailRefund(orderId){
    try {
        let response = await fetch('/admin/refund/detail/' + orderId);
        if(response.ok){
            let apiUtil = await response.json();
            let refundDetailDTO = apiUtil.response;
            let campName = document.querySelector('.camp_name');
            campName.value = refundDetailDTO.campName;
            let campImage = document.querySelector('.camp_image');
            campImage.src = `../..${refundDetailDTO.campImage}`;
            let campFieldImage = document.querySelector('.camp_field_image');
            campFieldImage.src = `../..${refundDetailDTO.campFieldImage}`;
            let userNickname = document.querySelector('.user_nickname');
            userNickname.innerText = refundDetailDTO.orderUserNickname;
            let reservationDate = document.querySelector('.reservation_date');
            reservationDate.innerText = `${refundDetailDTO.checkIn} ~ ${refundDetailDTO.checkOut}`;
            let selectedField = document.querySelector('.selected_field');
            selectedField.innerText = refundDetailDTO.selectedField;
            let orderDate = document.querySelector('.order_date');
            orderDate.innerText = refundDetailDTO.orderAt;
            let refundDate = document.querySelector('.refund_date');
            refundDate.innerText = refundDetailDTO.refundAt;
            let refund = document.querySelector('.refund');
            refund.innerText = `- ${refundDetailDTO.refund}원`;
        } else {
            console.error("실패", response.statusText);
        }
    } catch (e) {
        console.error("실패", e.message);
    }
}

