let CHECK_STATUS = false;
let LOGIN_ID_STATUS = false;
let NICKNAME_STATUS = false;
let EMAIL_STATUS = false;

async function checkDupleLoginId(){

    let inputLoginId = document.querySelector("#loginId");
    let loginId = inputLoginId.value;

    await fetch("http://localhost:8085/members/check/id?loginId=" + loginId)
    .then(

        (response) => {
            return response.json();
        }
    )
    .then(

        (data) => {

            let idCheck = data;

            if(idCheck.status || loginId === ""){

            LOGIN_ID_STATUS = false;
            console.log(LOGIN_ID_STATUS);
            alert("가입할 수 없는 아이디 입니다.");

            }else{

            LOGIN_ID_STATUS = true;
            console.log(LOGIN_ID_STATUS);
            alert("가입할 수 있는 아이디 입니다.")

            }
        }
    )
    .catch(
        (error) => {
            console.log(error);
        }
    )
}

async function checkDupleNickname(){

    let inputNickname = document.querySelector("#nickname");
    let nickname = inputNickname.value;

    await fetch("http://localhost:8085/members/check/nickname?nickname=" + nickname)
    .then(

        (response) => {
            return response.json();
        }
    )
    .then(

        (data) => {

            let nicknameCheck = data;

            if(nicknameCheck.status || nickname === ""){

            NICKNAME_STATUS = false;
            console.log(NICKNAME_STATUS);
            alert("가입할 수 없는 닉네임 입니다.");

            }else{

            NICKNAME_STATUS = true;
            console.log(NICKNAME_STATUS);
            alert("사용할 수 있는 닉네임 입니다.")

            }
        }
    )
    .catch(
        (error) => {
            console.log(error);
        }
    )
}

async function checkDupleEmail(){

    let inputEmail = document.querySelector("#email");
    let email = inputEmail.value;

    await fetch("http://localhost:8085/members/check/email?email=" + email)
    .then(

        (response) => {
            return response.json();
        }
    )
    .then(

        (data) => {

            let emailCheck = data;

            if(emailCheck.status || email === ""){

            EMAIL_STATUS = false;
            console.log(EMAIL_STATUS);
            alert("가입할 수 없는 이메일 입니다.");

            }else{

            EMAIL_STATUS = true;
            console.log(EMAIL_STATUS);
            alert("사용할 수 있는 이메일 입니다.")

            }
        }
    )
    .catch(
        (error) => {
            console.log(error);
        }
    )
}

function checkStatus(){

    if(LOGIN_ID_STATUS){
        CHECK_STATUS = true;
    }else{
        CHECK_STATUS = false;
    }

    if( !CHECK_STATUS ){
    alert("중복확인을 해주시기 바랍니다.");
    return false;
    }

}