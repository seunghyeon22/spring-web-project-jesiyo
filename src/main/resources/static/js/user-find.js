
// 전화번호에 숫자만 입력가능 알림창
    function telCheck() {
        let tel = document.querySelector(".tel").value;

        if (tel && isNaN(tel)) {
            alert("숫자만 입력해주세요.");
        }
    }

    document.querySelector("#tel").addEventListener('keyup', telCheck);
    document.querySelector("#telforpw").addEventListener('keyup', telCheck);

// 아이디 찾기
    async function findid() {
        let tel = document.querySelector('#tel').value;
        let name = document.querySelector('#name').value;

        const response = await fetch('/user-find-id', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({tel: tel, name: name}),
        });
        if (!response.ok) {
            throw new Error(`${response.status} ${response.statusText}`);
        }
        let result = await response.json();
        let findResult = document.querySelector("#find-result");

        if (result.result !== 'null') {
            findResult.textContent = `회원님의 아이디는 ${result.result}입니다.`;
        }else {
            findResult.textContent = `입력하신 회원정보가 존재하지 않습니다.`
        }
    }

// 비밀번호 찾기
    async function findpw() {
        let tel = document.querySelector('#telforpw').value;
        let name = document.querySelector('#nameforpw').value;
        let id = document.querySelector('#idforpw').value;

        const response = await fetch('/user-find-pw', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({tel: tel, name: name, username:id}),
        });
        if (!response.ok) {
            throw new Error(`${response.status} ${response.statusText}`);
        }
        let result = await response.json();

        if(result == 0){
            alert("일치하는 회원정보가 없습니다.")
        }
        if(result !== 0){
            alert("비밀번호 변경페이지로 이동합니다.")
            location.href=`/change-pw-form/${result}`;
        }
    }

