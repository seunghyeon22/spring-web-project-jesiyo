

headerLoad();

async function headerLoad(){
    let page = document.querySelector(".t1-auth-links");
    let userDetailes = await getAuthentication();
    if(userDetailes==null){
        page.innerHTML=`
        <div>
        <a href="/login-form">로그인</a>
        <a href="/join-form">회원가입</a>
        </div>
        `
    }else {
        if (userDetailes.role === "ROLE_USER") {
            page.innerHTML = `
            <div>
            <a href="/logout">로그아웃</a>
            <a href="/user-info/${userDetailes.id}">마이페이지</a>
            </div>
            `;
        } else if (userDetailes.role === "ROLE_ADMIN,ROLE_USER") {
            page.innerHTML = `
            <div>
            <a href="/logout">로그아웃</a>
            <a href="/confirm-report">관리자페이지</a>
            </div>
            `;
        }
    }
}

async function getAuthentication(){
    let url = `/api/v1/authentication`;
    let response = await fetch(url);
    let responseData = await response.json();
    let userDetails = responseData.data;
    return userDetails;
}
