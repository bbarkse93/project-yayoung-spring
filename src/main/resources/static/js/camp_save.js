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

function addPhotoField() {
    if(photoCount > 4){
        alert("최대 5장까지만 추가할 수 있습니다.")
        return;
    }
    // 새로운 레이블 생성
    let newLabel = document.createElement("label");
    newLabel.setAttribute("id", "camp_pic" + photoCount);
    newLabel.setAttribute("for", "camp_photo" + photoCount);
    newLabel.className = "camp_select_form";
    newLabel.innerHTML = '<img src="" class="camera_icon">' +
        '<span class="custom_font_point3"></span>';

    // 새로운 인풋 필드 생성
    let newInput = document.createElement("input");
    newInput.setAttribute("type", "file");
    newInput.setAttribute("id", "camp_photo" + photoCount);
    newInput.setAttribute("name", "campPhotoList");
    newInput.setAttribute("accept", "image/*");
    newInput.setAttribute("onchange", "changeImage(this.id, 'camp_pic" + photoCount + "', 'camp_pic_style')");
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


function plusCampSite(){
    let campFieldInsertForm = document.querySelector('.camp_site_insert_form');
    let div = document.createElement('div');
    div.className = "add_camp_site";
    div.innerHTML = `
        <input type=text class="camp_site_name_input input"
               id="camp_site_name${campSiteCount}" placeholder="구역 이름" name="campFieldDTOList[${campSiteCount}].fieldName">
        <input type="text" class="camp_site_price_input input"
               id="camp_site_price${campSiteCount}" placeholder="금액(숫자만 입력)"
               name="campFieldDTOList[${campSiteCount}].price">
    `;
    campFieldInsertForm.appendChild(div);

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

//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("sample4_jibunAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

            var guideTextBox = document.getElementById("sample4_jibunAddress");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.value = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.value = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.value = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
