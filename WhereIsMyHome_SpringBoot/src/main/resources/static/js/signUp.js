const idValidEl = document.getElementById('id-sign-up-valid');
const passwordValidEl = document.getElementById('password-sign-up-valid');
const nameValidEl = document.getElementById('name-sign-up-valid');
const addressValidEl = document.getElementById('address-sign-up-valid');
const phoneNumberValidEl = document.getElementById(
  'phone-number-sign-up-valid'
);

// 회원 가입 클릭
const handleClickSignup = () => {
  const id = document.getElementById('id').value;
  const password = document.getElementById('password').value;
  const name = document.getElementById('name').value;
  const address = document.getElementById('address').value;
  const phoneNumber = document.getElementById('phone-number').value;
  
  // id 중복 여부 유효성 검사
  if(id) {
	fetch("/whereismyhome_be/user/" + userid)
		.then(response => response.text())
		.then(data => {
			 if(data == 0) {
				 // 사용 가능일 시 문구를 초록색으로 변경
				 idValidEl.setAttribute("style", "color: #12b886");
				 idValidEl.innerText = id + "는 사용할 수 있습니다.";
				 
				 if (id && password && name && address && phoneNumber) {
					 let form = document.querySelector("#form-join");
					 form.setAttribute("action", "/whereismyhome_be/user");
					 
					 form.submit();
				 }				 
			 } else {
				 idValidEl.innerText  = id + "는 사용할 수 없습니다.";
			 }
		});
  }
  
  // 문구 색 복구
  idValidEl.setAttribute("style", "color: #ce1212");
  

  // 공란 유효성 검사 
  if (!id) {
    idValidEl.innerText = '아이디를 입력하세요';
  } 
  if (!password) {
    passwordValidEl.innerText = '비밀번호를 입력하세요';
  }
  if (!name) {
    nameValidEl.innerText = '이름을 입력하세요';
  }
  if (!address) {
    addressValidEl.innerText = '주소를 입력하세요';
  }
  if (!phoneNumber) {
    phoneNumberValidEl.innerText = '전화번호를 입력하세요';
  }
};

// 회원 가입 모달 닫기
const handleClickSignupCancle = () => {
  document.querySelector('#btn-sign-up-close').click();

  idValidEl.innerText = '';
  passwordValidEl.innerText = '';
  nameValidEl.innerText = '';
  addressValidEl.innerText = '';
  phoneNumberValidEl.innerText = '';
};
