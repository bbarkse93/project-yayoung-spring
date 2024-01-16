/*FAQ 내용 조회*****************************************************/
async function fetchDetailNotice(noticeId) {
    try {
        let response = await fetch('/admin/notice/detail/' + noticeId);
        if (response.ok) {
        let apiUtil = await response.json();
        let noticeDetailDTO = apiUtil.response;
        let title = document.getElementById('update_title');
        title.placeholder = noticeDetailDTO.title;
        let content = document.getElementById('update_content');
        content.innerHTML = noticeDetailDTO.content;
        let updateNoticeId = document.getElementById('notice_id');
            updateNoticeId.value = noticeDetailDTO.noticeId;

        } else {
            console.error("실패", response.statusText);
        }
    } catch (e) {
        console.error("실패", e.message);
    }
}

/*FAQ 삭제*****************************************************/


async function fetchDeleteNotice(faqId){

    let userConfirmed = window.confirm("해당 공지사항을 삭제하시겠습니까?");

    if (userConfirmed) {
        try{
            let response = await fetch(`/admin/notice/delete/${faqId}`, {
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

async function fetchSaveNotice() {

    let title = document.getElementById('insert_title').value;
    let content = document.getElementById('insert_content').value;
    let dto = {};

    // if ((!title || !title.trim()) || (!content || !content.trim())) {
    //     alert("공백을 입력해주세요.");
    //     return false;
    // } else {
        dto = {
            title: title.trim(),
            content: content.trim(),
            userId: 2,
        };

        try {
            let response = await fetch(`/admin/notice/save`, {
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

async function fetchUpdateNotice() {

    let title = document.getElementById('update_title').value;
    let content = document.getElementById('update_content').value;
    let noticeId = document.getElementById('notice_id').value;

    let dto = {
        title: title,
        content: content,
        userId: 2,
    };

    console.log("제목 : " + title);
    console.log("내용 : " + content);
    console.log("noticeId : " + noticeId);


    try {
        let response = await fetch(`/admin/notice/update/${noticeId}`, {
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


