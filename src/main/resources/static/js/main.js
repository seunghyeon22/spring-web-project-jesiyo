all();

 async function all(){
     let carouselInner = document.querySelector(".t1-hotCategory");
    let data = await categorySelectAll();
    let s =Math.ceil(data.length/4);
    let str = ``;
    for(let i= 0; i<s; i++){
        str += `<div class="t1-carousel-item carousel-item"><div class="t1-category-box d-flex justify-content-center" style="gap: 20px;"></div></div>`
    }
     carouselInner.innerHTML=str;

    let carouselItem = document.querySelectorAll(".t1-carousel-item");
    if (carouselItem.length > 0) {
         carouselItem[0].classList.add ('active');
     }

    let categoryBox = document.querySelectorAll(".t1-category-box");
    let num = 0;
    let str2 =``;
    for(let i=0; i<data.length; i++){
        str2+=`
             <div class="t1-category">
                 <input type="hidden" value="${data[i].id}">
                 <a href="#"><img src="${data[i].imgUrl}" class="rounded" alt="디지털기기"></a>
                 <div class="t1-category-name">${data[i].name}</div>
             </div>      `
        if((i+1)%4==0){
            if (num < categoryBox.length) {
                categoryBox[num].innerHTML = str2;
                num++;
                str2 = ``;
            }
        }
    }
     if (str2 !== '' ) {
         categoryBox[num].innerHTML = str2;
     }
}

async function categorySelectAll(){
    let url = "/api/v1/category"
        let response = await fetch(url);
        let responseData = await response.json();
        if(!responseData.success){
            throw new Error("네트워크 응답에 문제가 있습니다.");
        }
        let data = responseData.data;
        return data;

}
