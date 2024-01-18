/*배너 빈값 체크*****************************************************/

document.getElementById('banner_form').addEventListener('submit', function (event) {
    let file = document.getElementById('camp_photo');
    if(file.files.length === 0){
        alert("배너를 등록해주세요.");
        event.preventDefault();
        return false;
    }
});

/*배너 토글*****************************************************/

$(function (){
    $(".btn_button").click(function (){
        // 현재 클릭된 요소의 id에서 bannerId 추출
        let bannerId = $(this).attr('id').replace('banner_toggle', '');

        // 해당 bannerId를 이용하여 toggle할 요소 선택 및 처리
        $("#toggle" + bannerId).toggle();
    });
});

/*배너 미리보기*****************************************************/

function changeImage(inputId, containerId, styleClass) {
    let inputElement = document.getElementById(inputId);

    // 파일이 선택되었는지 확인
    if (inputElement.files.length === 0) {
        // 파일이 선택되지 않았을 경우 처리 (예: 경고 메시지)
        alert("파일을 선택해주세요.");
        return;
    }

    // 선택된 파일 가져오기
    let f = inputElement.files[0];

    let reader = new FileReader();
    reader.onload = function (e2) {

        let size = 1024 * 1024 * 20;
        if (f.size > size) {
            alert("파일 크기는 20MB 이상 클 수 없습니다.");
            return;
        }

        let previewEl = document.querySelector("#" + inputId);
        let parentContainer = document.querySelector("#" + containerId);
        parentContainer.innerHTML = '';

        let newImg = document.createElement("img");
        newImg.setAttribute("src", e2.target.result);

        let parentElement = document.getElementById(containerId);
        parentElement.appendChild(newImg);
        newImg.classList.add(styleClass);
        previewEl.setAttribute("src", e2.target.result);
    }

    reader.readAsDataURL(f);
}

/*배너 삭제*****************************************************/

async function fetchDeleteBanner(bannerId){

    let userConfirmed = window.confirm("해당 배너를 삭제하시겠습니까?");

    if (userConfirmed) {
        try{
            let response = await fetch(`/admin/camp/banner/delete/${bannerId}`, {
                method: "DELETE",
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