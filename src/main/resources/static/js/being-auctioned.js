
async function deleteGoods(goodsId){
    let url = `/api/v1/bid-delete/${goodsId}`
    let response = await fetch(url,{
        method : "DELETE"
    });
    let responseData = await response.json();
    if(!responseData.success){
        alert("잘못되었습니다.")
    }
    window.location.href="/myPage-being-auctioned";
    console.log(responseData);
}
