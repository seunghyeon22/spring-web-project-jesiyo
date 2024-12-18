// 전화번호에 숫자만 입력가능 알림창
function telCheck() {
    let tel = document.getElementById('tel').value;

    if (isNaN(tel)) {
        alert("숫자만 입력해주세요.");
    }
}

document.getElementById('tel').addEventListener('keyup', telCheck);

// 우편번호 검색
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;


            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if (data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if (data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}

// 현재 비밀번호 일치 여부
async function checkDbPassword() {
    let password = document.querySelector('#password').value;
    let newpassword = document.querySelector('#pw1').value;
    let response = await fetch('/s/user-info/pw-change', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({password: password, newPassword: newpassword}),
    });
    let result = await response.json();
    console.log(result);
    if (result.result == 1) {
        alert('비밀번호 변경이 완료되었습니다.');
        location.href = '/s/user-info/';
    } else if (result.result == 0) {
        alert('잘못된 비밀번호를 입력하셨습니다.');
    } else {
        alert('error');
    }

}

// 비밀번호 유효성 검사
function pwCheck(password) {
    const hasLetter = /[a-zA-Z]/.test(password); // 영문자 포함
    const hasNumber = /[0-9]/.test(password); // 숫자 포함
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password); // 특수문자 포함

    if (password.length < 8) {
        isPasswordValid = false;
        return '비밀번호는 최소 8자 이상이어야 합니다.';
    } else if (!hasLetter) {
        isPasswordValid = false;
        return '비밀번호에는 영문자가 포함되어야 합니다.';
    } else if (!hasNumber) {
        isPasswordValid = false;
        return '비밀번호에는 숫자가 포함되어야 합니다.';
    } else if (!hasSpecialChar) {
        isPasswordValid = false;
        return '비밀번호에는 특수문자가 포함되어야합니다.';
    } else {
        isPasswordValid = true;
        return "정상입니다";
    }
}

// 비밀번호 일치 확인
function pwAcc() {
    const pw1 = document.querySelector('#pw1').value;
    const pw2 = document.querySelector('#pw2').value;

    const pwck = document.querySelector('#pwck');
    const pwvalid = document.querySelector('#pwvalid');

    const msg = pwCheck(pw1);

    pwvalid.style.color = 'orange';
    pwvalid.textContent = msg;

    if (pw1 && pw2 != null) {
        if (pw1 == pw2) {
            pwck.textContent = '비밀번호 일치';
            pwck.style.color = 'green';
            isPasswordSame = true;
        } else {
            pwck.textContent = '비밀번호 불일치';
            pwck.style.color = 'red';
            isPasswordSame = false;
        }
    }
}

document.getElementById('pw1').addEventListener('keyup', pwAcc);
document.getElementById('pw2').addEventListener('keyup', pwAcc);


const input = document.getElementById('ssn-input');
input.addEventListener('input', function () {
    this.value = this.value.replace(/[^0-9]/g, '');
    if (this.value.length > 7) {
        this.value = this.value.slice(0, 7);
    }
});

//계좌실명확인
async function checkAccount(){
    const bankCode = document.querySelector('#bankCode').value;
    const bankNum = document.querySelector('#bankNum').value;

    try {
        const response = await fetch('/check-account', {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                bankCode: bankCode,
                bankNum: bankNum
            })
        });

        if (response.ok) {
            const data = await response.json();
            if (data.bankHolderInfo) {
                alert(`예금주명: ${data.bankHolderInfo}`);
            } else {
                alert('예금주 정보를 확인할 수 없습니다.');
            }
        } else {
            throw new Error('서버 오류');
        }
    } catch (error) {
        console.error('오류 발생:', error);
        alert('오류가 발생했습니다.');
    }
}

// 확인한 계좌 사용하기
function useAccount(){
    let account = document.querySelector('#bankNum').value;
    let account1 = document.querySelector('#account');

    account1.value = account;
    closeModal();
}

function closeModal() {
    const modal = bootstrap.Modal.getInstance(document.querySelector('#exampleModal'));
    if (modal) modal.hide();
}
