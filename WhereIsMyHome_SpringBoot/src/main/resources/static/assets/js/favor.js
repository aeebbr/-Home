// 관심 지역 등록 핸들러 
const handleClickAddRegion = () => {
	const sidoEl = document.querySelector('#sido');
	const gugunEl = document.querySelector('#gugun');
	const dongEl = document.querySelector('#dong');
	
	const sidoCode = sidoEl.value;
	const gugunCode = gugunEl.value;
	const dongCode = dongEl.value;
	
	const sidoName = sidoEl.options[sidoEl.selectedIndex].text;
	const gugunName = gugunEl.options[gugunEl.selectedIndex].text;
	const dongName = dongEl.options[dongEl.selectedIndex].text;
	
	location.href = `/favor/insert?sidoCode=${sidoCode}&gugunCode=${gugunCode}&dongCode=${dongCode}&sidoName=${sidoName}&gugunName=${gugunName}&dongName=${dongName}`;
};
