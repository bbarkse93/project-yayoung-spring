/*FAQ 내용 조회*****************************************************/
async function fetchDetailFaq(faqId) {
    try {
        let response = await fetch('/admin/faq/detail/' + faqId);
        if (response.ok) {
        let apiUtil = await response.json();
        let faqDetailDTO = apiUtil.response;
        let title = document.getElementById('update_title');
        title.placeholder = faqDetailDTO.title;
        let content = document.getElementById('update_content');
        content.innerHTML = faqDetailDTO.content;
        let updateFaqId = document.getElementById('faq_id');
        updateFaqId.value = faqDetailDTO.faqId;

        } else {
            console.error("실패", response.statusText);
        }
    } catch (e) {
        console.error("실패", e.message);
    }
}

/*FAQ 삭제*****************************************************/


async function fetchDeleteFaq(faqId){

    let userConfirmed = window.confirm("해당 FAQ를 삭제하시겠습니까?");

    if (userConfirmed) {
        try{
            let response = await fetch(`/admin/faq/delete/${faqId}`, {
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

/*FAQ 등록*****************************************************/

async function fetchSaveFaq() {

    let title = document.getElementById('insert_title').value;
    let content = document.getElementById('insert_content').value;
    let boardCategoryId = document.getElementById('insert_column').value;
    let dto = {};

    // if ((!title || !title.trim()) || (!content || !content.trim())) {
    //     alert("공백을 입력해주세요.");
    //     return false;
    // } else {
        dto = {
            title: title.trim(),
            content: content.trim(),
            boardCategoryId: boardCategoryId,
            userId: 2,
        };

        try {
            let response = await fetch(`/admin/faq/save`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(dto)
            });

            if (response.ok) {
                let apiUtil = await response.json();
                let success = apiUtil.response;
                alert(success);
                location.reload();
            } else {
                console.error("실패", response.statusText);
            }
        } catch (e) {
            console.error("실패", e.message);
        }
    // }
}

/*FAQ 수정*****************************************************/

async function fetchUpdateFaq() {

    let title = document.getElementById('update_title').value;
    let content = document.getElementById('update_content').value;
    let boardCategoryId = document.getElementById('update_column').value;
    let faqId = document.getElementById('faq_id').value;

    let dto = {
        title: title,
        content: content,
        boardCategoryId: boardCategoryId,
        userId: 2,
    };

    console.log("제목 : " + title);
    console.log("내용 : " + content);
    console.log("boardCategoryId : " + boardCategoryId);
    console.log("faqId : " + faqId);



    try {
        let response = await fetch(`/admin/faq/update/${faqId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dto)
        });

        if (response.ok) {
            let apiUtil = await response.json();
            let success = apiUtil.response;
            alert(success);
            location.reload();
        } else {
            console.error("실패", response.statusText);
        }
    } catch (e) {
        console.error("실패", e.message);
    }
}


/*alert 창*****************************************************/


let updateButton = document.querySelector('.update_submit');
updateButton.addEventListener("click", function () {
    alert("수정이 완료되었습니다.");
});

let submitButton = document.querySelector('.insert_submit');
submitButton.addEventListener("click", function () {
    alert("등록이 완료되었습니다.");
});

/*탭 이동*****************************************************/

// url 이동 시 아래의 함수 실행
window.onload = function () {
    getCategoryIdFromURL();
}

// url의 categoryId로 탭 이동하기
function getCategoryIdFromURL() {
    const urlParams = new URL(location.href).searchParams;
    let categoryId = urlParams.get('categoryId');
    if (categoryId == null) {
        categoryId = 1;
    }
    tabMove(categoryId);
}

function tabMove(categoryId) {
    let dataTab = 'tab-' + categoryId;
    let column_id = categoryId;
    let column = $("#" + categoryId)[0];
    let child = column.children[0];
    let column_name = child.textContent;
    console.log("column 이름 : " + column_name)

    $('.column_id').val(column_id);
    $('.column_name').text(column_name);

    $('ul.tabs li').removeClass('current');
    $('.tab-content').removeClass('current');

    $("#" + categoryId).addClass('current');
    $("#" + dataTab).addClass('current')
}


