async function fetchReview(campId) {
    try {
        let response = await fetch('/admin/camp/review/' + campId);
        if (response.ok) {
            let apiUtil = await response.json();
            let campReviewDTO = apiUtil.response;
            let campName = document.getElementById('campName');
            let campCount = document.getElementById('campCount');
            campName.innerHTML = campReviewDTO.campName;
            campCount.innerHTML = campReviewDTO.campTotalRating;

        } else {
            console.error("실패", response.statusText);
        }
    } catch (e) {
        console.error("실패", e.message);
    }

}



    // fetch로 새로운 데이터 받아오기
    async function fetchFundingList(page) {
    let response = await fetch('/admin/funding/confirm/more-data?page=' + page);
    let responseBody = await response.json();

    if (responseBody.success) {
    return responseBody.response;
} else {
    throw new Error(responseBody.error);
}
}
    // 로딩 딜레이 주기
    setTimeout(async () => {
    try {
    const newData = await fetchFundingList(currentPage);
    newData.forEach((funding) => {
    var newElement = '<div class="p_section1 p_box">' +
    '<button type="button" style="border: none; background: transparent" data-bs-toggle="modal" data-bs-target="#j_fund_modal" data-id="' + funding.fundingId + '" data-name="' + funding.movieName + '">' +
    '<img src="' + funding.movieThumbnail + '" alt="영화사진">' +
    '</button>' +
    '<div class="p_list">' +
    '<p class="p_p1">' + funding.movieName + '</p>' +
    '<p class="p_p2">감독:' + funding.director + '</p><br>' +
    '<div class="p_flex">' +
    '<p class="p_p3">' + funding.fundingRate + '</p>' +
    '<p class="p_p4" style="margin-left: 5px">% 달성</p><br>' +
    '<p class="p_p5">' + funding.endDate + '</p><br>' +
    '</div>' +
    '<div class="p_flex">' +
    '<p class="p_p6" style="margin-top: 25px">' + formatPrice(funding.presentPrice) + '</p>' +
    '<p class="p_p7" style="margin-top: 15px">원 달성</p><br>' +
    '<p class="p_p8" style="margin-left: 5px;>참여 ' + funding.peopleCount + '</p>' +
    '</div>' +
    '</div>' +
    '</div>';
    // Append new content to the container (assumes you have a container with id 'fund_container')
    $('#fund_container').append(newElement);
});
    currentPage++;
} catch (error) {
    console.error('Error fetching data:', error);
} finally {
    isLoading = false;
}
}, 1000);

    var scrollToTopBtn = document.getElementById("scrollToTopBtn");


