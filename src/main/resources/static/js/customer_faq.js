
// 피드백 메시지를 표시하는 함수
function showFeedbackMessage(title) {
    var feedbackMessage = document.getElementById(`feedbackMessage-${title}`);
    feedbackMessage.style.display = 'block';

    // 2초 후에 메시지를 숨김
    setTimeout(function() {
        feedbackMessage.style.display = 'none';
    }, 3000);
}



/*FAQ 내용 조회*****************************************************/
async function fetchDetailFaq(faqId) {
    try {
        let response = await fetch('/admin/faq/detail/' + faqId);
        if (response.ok) {
        let apiUtil = await response.json();
        let faqDetailDTO = apiUtil.response;
        let title = document.getElementById('update_title');
        title.value = faqDetailDTO.title;
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

    if (title.trim() === '' || content.trim() === '') { // title.value.trim()과 content.value.trim() 대신 title.trim()과 content.trim()을 사용
        console.log("브레이크!!!!");
        setTimeout(function () {
            showFeedbackMessage('insert');
        }, 0);
    }else{
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
    }
}

/*FAQ 수정*****************************************************/
    async function fetchUpdateFaq() {
        let title = document.getElementById('update_title').value;
        let content = document.getElementById('update_content').value;
        let boardCategoryId = document.getElementById('update_column').value;
        let faqId = document.getElementById('faq_id').value;

        if (title.trim() === '' || content.trim() === '') { // title.value.trim()과 content.value.trim() 대신 title.trim()과 content.trim()을 사용
            console.log("브레이크!!!!");
            setTimeout(function () {
                showFeedbackMessage('update');
            }, 0);
        }else {
            let dto = {
                title: title,
                content: content,
                boardCategoryId: boardCategoryId,
                userId: 2,
            };
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
    }

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


