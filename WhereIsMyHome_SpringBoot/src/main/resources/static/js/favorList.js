const titles = document.querySelectorAll('.favor-list-region');

titles.forEach((title) => {
	title.addEventListener("click", () => {
		const t = title.childNodes;
		console.log(t);
						
		location.href = `/whereismyhome_be/apt?action=aptlist&sido=${t[1].innerText}&gugun=${t[3].innerText}&dong=${t[5].innerText}`;
	});
});

const delBtns = document.querySelectorAll('.favor-list-btn-del');

delBtns.forEach((delBtn) => {
	delBtn.addEventListener("click", () => {
		console.log(delBtn);
		location.href = `/whereismyhome_be/favor?act=delete&id=${delBtn.id}`;
	});
});
