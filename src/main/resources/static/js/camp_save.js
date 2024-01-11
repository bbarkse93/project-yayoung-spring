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

let photoCount = 1;

function addPhotoField() {
    if(photoCount > 4){
        alert("최대 5장까지만 추가할 수 있습니다.")
        return;
    }
    // 새로운 레이블 생성
    let newLabel = document.createElement("label");
    newLabel.setAttribute("id", "movie" + photoCount);
    newLabel.setAttribute("for", "photo" + photoCount);
    newLabel.className = "k_funding_upload_select_photo_pic_receive";
    newLabel.innerHTML = '<img src="" class="k_camera_icon">' +
        '<span class="k_star_class"></span>';

    // 새로운 인풋 필드 생성
    let newInput = document.createElement("input");
    newInput.setAttribute("type", "file");
    newInput.setAttribute("id", "photo" + photoCount);
    newInput.setAttribute("name", "moviePhotos");
    newInput.setAttribute("accept", "image/*");
    newInput.setAttribute("onchange", "changeUserPic(this.id, 'movie" + photoCount + "', 'k_funding_movie_pic_style', event)");
    newInput.className = "k_funding_upload_label";

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
    document.getElementById('movie' + (photoCount - 1)).remove();
    document.getElementById("photo" + (photoCount - 1)).remove();

    // photoCount 감소
    photoCount--;
}

let careerCount = 1;

//
function plusCareerMovie(){
    if(careerCount > 9) {
        alert("최대 10개까지 추가 가능합니다.");
        return;
    }
    let marginTopPx = 47 * careerCount;
    let InputCm = document.createElement("input");
    InputCm.setAttribute("type", "text");
    InputCm.setAttribute("id", "career_movie" + careerCount);
    InputCm.setAttribute("placeholder", "작품 이름");
    InputCm.setAttribute("name", "directorCareers");
    InputCm.className = "k_funding_upload_career_input";

    let InputCmY = document.createElement("input");
    InputCmY.setAttribute("type", "text");
    InputCmY.setAttribute("id", "career_movie_year" + careerCount);
    InputCmY.setAttribute("placeholder", "작품 년도");
    InputCmY.setAttribute("name", "directorCareerYears");
    InputCmY.className = "k_funding_upload_movie_year";

    let InputMarginTop = document.createElement("div");
    InputMarginTop.setAttribute("id","k_cm_margin_top" + careerCount);
    InputMarginTop.setAttribute("style","margin-top: "+ marginTopPx + "px;");


    // 컨테이너에 새로운 레이블과 인풋 필드 추가
    document.getElementById("career_movie").appendChild(InputCm);
    document.getElementById("career_movie_year").appendChild(InputCmY);
    document.getElementById("k_cm_margin_top").appendChild(InputMarginTop);
    careerCount++;
}

function minusCareerMovie(){
    if(careerCount < 2) {
        alert("더 이상 삭제할 수 없습니다.");
        return;
    }
    // 삭제할 요소의 ID로 해당 요소를 찾아서 제거
    document.getElementById('career_movie' + (careerCount- 1)).remove();
    document.getElementById("career_movie_year" + (careerCount - 1)).remove();
    document.getElementById("k_cm_margin_top" + (careerCount - 1)).remove();


    // careerCount 감소
    careerCount--;
}

let awardsCount = 1;


function plusAwardsMovie(){
    if(awardsCount > 9) {
        alert("최대 10개까지 추가 가능합니다.");
        return;
    }
    let marginTopPx = 47 * awardsCount;
    let InputAm = document.createElement("input");
    InputAm.setAttribute("type", "text");
    InputAm.setAttribute("id", "awards_movie" + awardsCount);
    InputAm.setAttribute("placeholder", "작품 이름");
    InputAm.setAttribute("name", "directorAwards");
    InputAm.className = "k_funding_awards_movie_input";

    let InputAmY = document.createElement("input");
    InputAmY.setAttribute("type", "text");
    InputAmY.setAttribute("id", "awards_movie_year" + awardsCount);
    InputAmY.setAttribute("placeholder", "작품 년도");
    InputAmY.setAttribute("name", "directorAwardYears");
    InputAmY.className = "k_funding_upload_movie_year";

    // let InputMarginTop = document.createElement("div");
    // InputMarginTop.setAttribute("id","k_am_margin_top" + awardsCount);
    // InputMarginTop.setAttribute("style","margin-top: "+ marginTopPx + "px;");


    // 컨테이너에 새로운 레이블과 인풋 필드 추가
    document.getElementById("awards_movie").appendChild(InputAm);
    document.getElementById("awards_movie_year").appendChild(InputAmY);
    // document.getElementById("k_am_margin_top").appendChild(InputMarginTop);
    awardsCount++;
}

function minusAwardsMovie(){
    if(awardsCount < 2) {
        alert("더 이상 삭제할 수 없습니다.");
        return;
    }
    // 삭제할 요소의 ID로 해당 요소를 찾아서 제거
    document.getElementById("awards_movie" + (awardsCount- 1)).remove();
    document.getElementById("awards_movie_year" + (awardsCount - 1)).remove();
    // document.getElementById("k_cm_margin_top" + (awardsCount - 1)).remove();

    // awardsCount 감소
    awardsCount--;
}

let actorCount = 1;


function plusActor(){
    if(actorCount > 9) {
        alert("최대 10개까지 추가 가능합니다.");
        return;
    }
    let InputAm = document.createElement("input");
    InputAm.setAttribute("type", "text");
    InputAm.setAttribute("id", "movie_actor" + actorCount);
    InputAm.setAttribute("placeholder", "배우 이름");
    InputAm.setAttribute("name", "actors");
    InputAm.className = "k_funding_awards_movie_input";

    let InputAmY = document.createElement("input");
    InputAmY.setAttribute("type", "text");
    InputAmY.setAttribute("id", "movie_actor_role" + actorCount);
    InputAmY.setAttribute("placeholder", "배역");
    InputAmY.setAttribute("name", "actorRoles");
    InputAmY.className = "k_funding_upload_movie_year";

    // let InputMarginTop = document.createElement("div");
    // InputMarginTop.setAttribute("id","k_am_margin_top" + awardsCount);
    // InputMarginTop.setAttribute("style","margin-top: "+ marginTopPx + "px;");


    // 컨테이너에 새로운 레이블과 인풋 필드 추가
    document.getElementById("movie_actor").appendChild(InputAm);
    document.getElementById("movie_actor_role").appendChild(InputAmY);
    // document.getElementById("k_am_margin_top").appendChild(InputMarginTop);
    actorCount++;
}

function minusActor(){
    if(actorCount < 2) {
        alert("더 이상 삭제할 수 없습니다.");
        return;
    }
    // 삭제할 요소의 ID로 해당 요소를 찾아서 제거
    document.getElementById("movie_actor" + (actorCount- 1)).remove();
    document.getElementById("movie_actor_role" + (actorCount - 1)).remove();
    // document.getElementById("k_cm_margin_top" + (awardsCount - 1)).remove();

    // awardsCount 감소
    actorCount--;
}

function handleClick(element) {
    alert('변경 불가능한 값입니다.');
}
