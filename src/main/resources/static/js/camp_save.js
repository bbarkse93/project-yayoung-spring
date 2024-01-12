let photoCount;
let campSiteCount;

window.onload = function (){
    photoCount = 1;
    campSiteCount = 1;
}

let checkIn = document.getElementById('check_in');
let checkOut = document.getElementById('check_out');
flatpickr(checkIn, {
    enableTime: true,
    noCalendar: true,
    dateFormat: "H:i",
});
flatpickr(checkOut, {
    enableTime: true,
    noCalendar: true,
    dateFormat: "H:i",
});

function changeUserPic(inputId, containerId, styleClass, e1) {
    let f = e1.srcElement.files[0];
    console.log(f.type);
    if (!f.type.match("image.*")) {
        alert("이미지를 등록해주세요");
        return;
    }
    let reader = new FileReader();
    reader.onload = function (e2) {
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


function addPhotoField() {

    if(photoCount > 4){
        alert("최대 5장까지만 추가할 수 있습니다.")
        return;
    }
    // 새로운 레이블 생성
    let newLabel = document.createElement("label");
    newLabel.setAttribute("id", "camp" + photoCount);
    newLabel.setAttribute("for", "photo" + photoCount);
    newLabel.className = "camera_receive_form";
    newLabel.innerHTML = '<img src="" class="camera_icon">' +
        '<span class="custom_font_point3"></span>';

    // 새로운 인풋 필드 생성
    let newInput = document.createElement("input");
    newInput.setAttribute("type", "file");
    newInput.setAttribute("id", "photo" + photoCount);
    newInput.setAttribute("name", "campPhotoList");
    newInput.setAttribute("accept", "image/*");
    newInput.setAttribute("onchange", "changeUserPic(this.id, 'camp" + photoCount + "', 'camp_pic_style', event)");
    newInput.className = "camp_pic_upload_label";

    // 컨테이너에 새로운 레이블과 인풋 필드 추가
    document.getElementById("pic_plus").appendChild(newLabel);
    document.getElementById("pic_plus").appendChild(newInput);

    // photoCount 증가
    photoCount++;
}

function deletePhotoField() {
    if(photoCount < 2) {
        alert("더 이상 삭제할 수 없습니다.");
        return;
    }
    // 삭제할 요소의 ID로 해당 요소를 찾아서 제거
    document.getElementById('camp' + (photoCount - 1)).remove();
    document.getElementById("photo" + (photoCount - 1)).remove();

    // photoCount 감소
    return photoCount --;
}


//
function plusCampSite(){
    let inputName = document.createElement("input");
    inputName.setAttribute("type", "text");
    inputName.setAttribute("id", "camp_site_name" + campSiteCount);
    inputName.setAttribute("placeholder", "구역 이름");
    inputName.setAttribute("name", "campSiteName");
    inputName.className = "camp_site_name_input input";

    let inputPrice = document.createElement("input");
    inputPrice.setAttribute("type", "text");
    inputPrice.setAttribute("id", "camp_site_price" + campSiteCount);
    inputPrice.setAttribute("placeholder", "금액(숫자만 입력)");
    inputPrice.setAttribute("name", "campSitePrice");
    inputPrice.className = "camp_site_price_input input";

    let inputDiv = document.createElement("div");
    inputDiv.className = "add_camp_site";

    inputDiv.appendChild(inputName);
    inputDiv.appendChild(inputPrice);


    // 컨테이너에 새로운 레이블과 인풋 필드 추가
    document.querySelector(".camp_site_insert_form").appendChild(inputDiv);

    campSiteCount++;
}

function minusCampSite(){
    if(campSiteCount < 2) {
        alert("더 이상 삭제할 수 없습니다.");
        return;
    }
    // 삭제할 요소의 ID로 해당 요소를 찾아서 제거
    document.getElementById('camp_site_name' + (campSiteCount- 1)).remove();
    document.getElementById("camp_site_price" + (campSiteCount - 1)).remove();


    // careerCount 감소
    return campSiteCount --;
}

function handleClick(element) {
    alert('변경 불가능한 값입니다.');
}
