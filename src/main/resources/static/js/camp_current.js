async function fetchReview(campId) {
    try {
        let response = await fetch('/admin/camp/review/' + campId);
        if (response.ok) {
            let apiUtil = await response.json();
            let campReviewDTO = apiUtil.response;
            let campName = document.getElementById('campName');
            let campCount = document.getElementById('campCount');
            let ratingClean = document.getElementById('clean');
            let ratingManagement = document.getElementById('management');
            let ratingFriend = document.getElementById('friend');
            campName.innerHTML = campReviewDTO.campName;
            campCount.innerHTML = campReviewDTO.campTotalRating;
            ratingClean.innerHTML =
                ratingStar(0, campReviewDTO.cleanliness)
                + ratingStar(1, campReviewDTO.cleanliness)
                + ratingStar(2, campReviewDTO.cleanliness)
                + ratingStar(3, campReviewDTO.cleanliness)
                + ratingStar(4, campReviewDTO.cleanliness);
            ratingManagement.innerHTML =
                ratingStar(0, campReviewDTO.managementness)
                + ratingStar(1, campReviewDTO.managementness)
                + ratingStar(2, campReviewDTO.managementness)
                + ratingStar(3, campReviewDTO.managementness)
                + ratingStar(4, campReviewDTO.managementness);
            ratingFriend.innerHTML =
                ratingStar(0, campReviewDTO.friendliness)
                + ratingStar(1, campReviewDTO.friendliness)
                + ratingStar(2, campReviewDTO.friendliness)
                + ratingStar(3, campReviewDTO.friendliness)
                + ratingStar(4, campReviewDTO.friendliness);

            let reviewTbody = document.getElementById('review_tbody');
            reviewTbody.innerHTML = "";
            let reviewCount = document.querySelector('.custom_font_point1');
            reviewCount.innerHTML = campReviewDTO.reviewDTOList.length;
            campReviewDTO.reviewDTOList.forEach((review) => {
                let tr = document.createElement('tr');
                tr.className = "review_td";
                tr.innerHTML = `
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
                        <button class="review_button" onclick="deleteReview(${review.reviewId}, ${campReviewDTO.campId})">삭제</button>
                    </td>
                `;
                reviewTbody.appendChild(tr);
            })

        } else {
            console.error("실패", response.statusText);
        }
    } catch (e) {
        console.error("실패", e.message);
    }
}

async function deleteReview(reviewId, campId){
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
                fetchReview(campId);
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


