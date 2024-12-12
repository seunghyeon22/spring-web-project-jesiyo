
let list = document.querySelector(".my__list__item__box2");
let currentUrl = window.location.href;
let paths = currentUrl.split("/");
let pageNum = 2;
let line =15;

// 페이지가 로드될 때 체크된 상태를 복원
document.addEventListener('DOMContentLoaded', function() {
        let radios = document.getElementsByName('category');
        radios.forEach(radio => {
            if (radio.value === paths[paths.length - 1]) {
                radio.checked = true;
            }
        })
    }
);

// 라디오 버튼의 체크된 상태로 페이지 이동
function redirectToUrl(event) {
    const url = event.target.value;
    // URL로 이동
    window.location.href = "/goods-list/"+url;
}

// 더보기 버튼 클릭시 url을 보고 카테고리에서 리스트 추가인지 헤더에서 리스트 추가인지 확인해서 비동기 요청
async function moreList(){
    let moreBtn = document.querySelector(".t1-more-btn");
    let radios = document.getElementsByName('category');
    let s=0;

        let category = 0;
        radios.forEach(radio => {
            if (radio.value === paths[paths.length - 1]) {
                category = radio.value;
            }
        });
        if (category === 0) {
            let queryString = window.location.search;
            let urlParams = new URLSearchParams(queryString);
            let selectValue = urlParams.get('select');
            let keywordValue = urlParams.get('keyword');
            moreSearchGoods(selectValue, keywordValue);
        }
        else {
            console.log(category);
            moreCategoryGoodsList(category);
        }
}

// 헤더에서 더보기 리스트 가져오기
async function moreSearchGoods(selectValue, keywordValue){
    let uri = `/api/v1/search-goods?select=${selectValue}&keyword=${keywordValue}&page=${pageNum}&line=${line}`;
    let response = await fetch(uri);
    let responseData = await response.json();
    if(!responseData.success){
        throw new Error("네트워크 응답에 문제가 있습니다.");
    }
    let data = responseData.data;
    plusHtml(data);
    pageNum++;
}

// 카테고리 별로 물품 리스트 가져오기
async function moreCategoryGoodsList(category){
    let uri = `/api/v1/search-goods/${category}?page=${pageNum}&line=${line}`;
    let response = await fetch(uri);
    let responseData = await response.json();
    if(!responseData.success){
        throw new Error("네트워크 응답에 문제가 있습니다.");
    }
    let data = responseData.data;
    plusHtml(data);
    pageNum++;
}
// 데이터 html에 담기
function plusHtml(data){
    let str =``;
    for(let i =0; i<data.length ;i++){
        str+=`
                <a href="/goods-detail/${data[i].id}" style="text-decoration: none; color: #f96506;">
                    <div class="card" style="border: 2px solid orange">
                        <input type="hidden" class="id" value="{{id}}">
                        <img class="card__img__top" src="${data[i].imgUrl}" alt="ListCard image">
                        <div class="card__body">
                        <h4 class="card__title">${data[i].title}</h4>
                        <p class="card__text">판매자: ${data[i].seller}</p>
                        <p class="card__text">카테고리: ${data[i].category}</p>
                        <p class="card__text">시작 입찰가:${data[i].startingPrice}</p>
                        <p class="card__text">최고 입찰가: ${data[i].tryPrice}</p>
                        <p class="card__text">${data[i].endAt}</p>
                        </div>
                    </div>
                </a>
                `;
    }
    list.innerHTML+=str;
}