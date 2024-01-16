
async function deleteUser(userId){

    let userConfirmed = window.confirm("해당 유저를 삭제하시겠습니까?");

    if (userConfirmed) {
        try{
            let response = await fetch(`/admin/user/delete/${userId}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
            });

            if(response.ok){
                let apiUtil = await response.json();
                let success = apiUtil.response;
                alert(success);
                location.reload();
            }else {
                console.error("실패", response.statusText);
            }
        }catch (e) {
            console.error("실패", e.message);
        }
    } else {
        return null;
    }
}

async function fetchUser(userId) {
    try {
        let response = await fetch('/admin/user/detail/' + userId);
        if (response.ok) {
            let apiUtil = await response.json();
            let userDetailDTO = apiUtil.response;
            let userImage = document.getElementById('user_image');
            let userNickname = document.getElementById('user_nickname');
            userImage.src = `../../../../..${userDetailDTO.userImage}`;
            userNickname.innerHTML = userDetailDTO.nickname;
            let orderCount = document.getElementById('order_count');
            orderCount.innerHTML = userDetailDTO.orderDTOList.length;
            let orderTbody = document.getElementById('order_tbody');
            orderTbody.innerHTML = "";
            userDetailDTO.orderDTOList.forEach((order) => {
                let orderTr = document.createElement('tr');
                orderTr.className = "order_td";
                orderTr.innerHTML = `
                    <td>${order.orderId}</td>
                    <td><button class="camp_route">${order.campName}</button></td>
                    <td>${order.checkInDate}</td>
                    <td>${order.checkOutDate}</td>
                    <td>${order.campField}</td>
                    <td>${order.price}원</td>
                    <td>${order.orderCreatedAt}</td>
                `;
                orderTbody.appendChild(orderTr);
            }); // 여기서 forEach 루프가 끝납니다.

            let reviewCount = document.getElementById('review_count');
            reviewCount.innerHTML = userDetailDTO.reviewDTOList.length;
            let reviewTbody = document.getElementById('review_tbody');
            reviewTbody.innerHTML = "";
            userDetailDTO.reviewDTOList.forEach((review) => {
                let reviewTr = document.createElement('tr');
                reviewTr.className = "review_td";
                reviewTr.innerHTML = `
                    <td>${review.reviewId}</td>
                    <td>${review.userNickname}</td>
                    <td style="text-align: start">${review.content}</td>
                    <td>                
                        ${ratingStar(0, review.totalRating)
                + ratingStar(1, review.totalRating)
                + ratingStar(2, review.totalRating)
                + ratingStar(3, review.totalRating)
                + ratingStar(4, review.totalRating)}
                    </td>
                    <td>${review.createAt}</td>
                    <td>
                        <button class="review_button" onclick="deleteReview(${review.reviewId}, ${userDetailDTO.userId})">삭제</button>
                    </td>
                `;
                reviewTbody.appendChild(reviewTr);
            }); // 여기서 forEach 루프가 끝납니다.

        } else {
            console.error("실패", response.statusText);
        }
    } catch (e) {
        console.error("실패", e.message);
    }
}

async function deleteReview(reviewId, userId){

    let userConfirmed = window.confirm("해당 리뷰를 삭제하시겠습니까?");

    if (userConfirmed) {
        try{
            let response = await fetch(`/admin/camp/review/delete/${reviewId}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
            });

            if(response.ok){
                let apiUtil = await response.json();
                let success = apiUtil.response;
                alert(success);
                fetchUser(userId);
            }else {
                console.error("실패", response.statusText);
            }
        }catch (e) {
            console.error("실패", e.message);
        }
    } else {
        return null;
    }

}




function ratingStar(index, rating){
    if (rating != null) {
        if (rating >= index + 1) { // full-star
            return '<img src="/static_images/icons/blue_star.png" width="14px">'
        } else if (rating >= index + 0.5) { // half-star
            return '<img src="/static_images/icons/half-star.png" width="14px">'
        }
    }
    return '<img src="/static_images/icons/gray_star.png" width="14px">'
}