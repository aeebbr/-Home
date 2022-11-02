// 로그인 버튼 클릭
const handleClickLogin = () => {
	const idValidEl = document.getElementById('id-login-valid');
	const passwordValidEl = document.getElementById('password-login-valid');

	const id = document.getElementById('login-id').value;
	const password = document.getElementById('login-password').value;
	
	if (id && password) {
		const form = document.querySelector("#form-login");
		form.setAttribute("action", "/user/login");
		form.submit();
	}
	if (!id) {
		idValidEl.innerText = '아이디를 입력하세요';
	}
	if (!password) {
		passwordValidEl.innerText = '비밀번호를 입력하세요';
	}
};

//const loginFailed = document.querySelector('.login-failed').innerText;

const openLogin = () => {
	const loginNav = document.querySelector('#nav-login');
	
	loginNav.click();
}

// 로그인 실패 문구가 존재한다면 모달 open
//if(loginFailed !== "") {
//	openLogin();
//}

// 로그인 모달 닫기
const handleClickloginCancle = () => {
//	const loginFailed = document.querySelector('#login-failed');
	
	document.querySelector('#btn-login-close').click();

	idValidEl.innerText = '';
	passwordValidEl.innerText = '';
// loginFailed.innerText = '';
	window.location.href ="/whereismyhome_be/index.jsp";	
};

// 모달 세팅
const setModal = () => {
	const infoNav = document.querySelector('#nav-info');
	infoNav.setAttribute("data-bs-toggle", "modal");
	infoNav.setAttribute("data-bs-target", "#info-modal");
	
	infoNav.click();
}

// 회원 정보 클릭
if(document.querySelector('#info-id')) {	
	if(document.querySelector('#info-id').innerText) {
		setModal();	
	} 
}


// 회원 정보 모달 닫기
const handleClickInfoClose = () => {
	document.querySelector('#btn-close-info').click();
	window.location.href ="/whereismyhome_be/index.jsp";	
};

// 회원 정보 수정
//document.querySelector('#btn-info-modi').addEventListener('click', () => {
const handleClickOpenModi = () => {
	const info = document.querySelector('#info-container');
	const title = document.querySelector('#info-modal h4');
	
	const id = document.querySelector('#info-id').innerText;
	const pw = document.querySelector('#info-password').innerText;
	const name = document.querySelector('#info-name').innerText;
	const addr = document.querySelector('#info-address').innerText;
	const pNumber = document.querySelector('#info-phone-number').innerText;
	
	title.innerText = "회원 정보 수정";
	
	info.innerHTML = `
  <div class="modal-body" id="user-form">
            <form id ="form-modify" method="post" action="/whereismyhome_be/user/modify">
              <div class="row">
                <div class="row mb-3">
                  <div class="col-md-3">
                    <label for="id-modi" class="form-label">아이디</label>
                  </div>
                  <div class="col-md-9">
                    <input type="text" class="form-control" id="id-modi" name="id" value="${id}" placeholder="아이디를 입력해 주세요"  readonly="readonly"/>
                    <div class="input-valid" id="id-info-valid"></div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="row mb-3">
                  <div class="col-md-3">
                    <label for="password-modi" class="form-label">비밀번호</label>
                  </div>
                  <div class="col-md-9">
                    <input type="password" class="form-control" id="password-modi" name="pw" value="${pw}"  placeholder="영문 숫자 포함 6자리 이상" />
                    <div class="input-valid" id="password-info-valid"></div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="row mb-3">
                  <div class="col-md-3">
                    <label for="name-modi" class="form-label">이름</label>
                  </div>
                  <div class="col-md-9">
                    <input type="text" class="form-control" id="name-modi" name="name" value="${name}" placeholder="이름을 입력해 주세요" />
                    <div class="input-valid" id="name-info-valid"></div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="row mb-3">
                  <div class="col-md-3">
                    <label for="address-modi" class="form-label">주소</label>
                  </div>
                  <div class="col-md-9">
                    <input type="text" class="form-control" id="address-modi" name="addr" value="${addr}" placeholder="주소를 입력해 주세요" />
                    <div class="input-valid" id="address-info-valid"></div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="row mb-3">
                  <div class="col-md-3">
                    <label for="phone-number-modi" class="form-label">전화번호</label>
                  </div>
                  <div class="col-md-9">
                    <input type="text" class="form-control" id="phone-number-modi" name="pNumber" value="${pNumber}" placeholder="010-xxxx-xxxx" />
                    <div class="input-valid" id="phone-number-info-valid"></div>
                  </div>
                </div>
              </div>
            </form>
          </div>
  `;

	const infoButtonContainer = document.querySelector('.info-button-container');

	infoButtonContainer.innerHTML = `
  <button type="button" id="btn-info-modi-confirm" class="btn-form-red btn btn-sm" onclick="handleClickInfoModi()">저장</button>
  <button type="button" id="btn-sign-up-cancle" class="btn-form-red btn-form-gray btn btn-sm" onclick="handleClickInfoClose()">취소</button>
 `;
};

// 회원 정보 수정 클릭
const handleClickInfoModi = () => {
	const idValidEl = document.getElementById('id-info-valid');
	const passwordValidEl = document.getElementById('password-info-valid');
	const nameValidEl = document.getElementById('name-info-valid');
	const addressValidEl = document.getElementById('address-info-valid');
	const phoneNumberValidEl = document.getElementById('phone-number-info-valid');

	idValidEl.innerText = '';
	passwordValidEl.innerText = '';
	nameValidEl.innerText = '';
	addressValidEl.innerText = '';
	phoneNumberValidEl.innerText = '';

	const id = document.getElementById('id-modi').value;
	const password = document.getElementById('password-modi').value;
	const name = document.getElementById('name-modi').value;
	const address = document.getElementById('address-modi').value;
	const phoneNumber = document.getElementById('phone-number-modi').value;

	if (id && password && name && address && phoneNumber) {
// localStorage.setItem('id', id);
// localStorage.setItem('password', password);
// localStorage.setItem('name', name);
// localStorage.setItem('address', address);
// localStorage.setItem('phoneNumber', phoneNumber);
//
// location.reload();
		
		document.querySelector('#form-modify').submit();
		
// location.href = "/whereismyhome_be/user/modify";
	}
	
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

// 회원 탈퇴 클릭
const handleClickDel = () => {
	if (window.confirm('탈퇴하시겠습니까?')) {
		window.location.href = "/whereismyhome_be/user/delete";
	}
};