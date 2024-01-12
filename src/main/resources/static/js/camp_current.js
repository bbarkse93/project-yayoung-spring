
    let currentPage = 2;
    let isLoading = false;
    window.onload = function () {
    console.log("온로드 실행");
    isLoading = false;
};

    $(window).scroll(function () {
    // 스크롤 이동 시 실행되는 코드
    console.log("제이쿼리 스크롤");
    if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
    // 페이지 하단에 도달했을 때만 추가 데이터 로드
    loadMoreData();
}

});


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

    // 마우스 스크롤 감지 후 새로운 데이터를 받아온 후 새로운 요소 생성하기
    function loadMoreData() {

    if (isLoading) {
    return;
}
    isLoading = true;

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

    window.addEventListener("scroll", function () {
    // 현재 스크롤 위치 가져오기
    var scrollPosition = window.scrollY;

    // 스크롤 위치가 300px 이상이면 버튼 표시, 아니면 숨김
    if (scrollPosition > 500) {
    scrollToTopBtn.style.display = "block";
} else {
    scrollToTopBtn.style.display = "none";
}
});

    // 버튼 클릭 시 맨 위로 스크롤하는 함수
    function scrollToTop() {
    window.scrollTo({
    top: 0,
    behavior: "smooth" // 부드러운 스크롤 적용
});
}

    // 버튼에 클릭 이벤트 리스너 추가
    scrollToTopBtn.addEventListener("click", scrollToTop);
}

    function formatPrice(number) {
    if(number === 0){
    return 0;
} else {
    const formatter = new Intl.NumberFormat('en-US');
    return formatter.format(number);
}
}

