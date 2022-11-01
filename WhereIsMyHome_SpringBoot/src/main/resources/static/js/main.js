fetch("/whereismyhome_be/addr?action=sido")
.then(response => response.json())
.then(data => {
    let sidoSel = document.querySelector("#sido");
    let init = document.createElement("option");
    init.textContent = "도/광역시";
    
    sidoSel.appendChild(init);
    data.addrList.forEach(sido => {
        let option = document.createElement("option");
        option.textContent = sido.name;
        option.value = sido.code;
        
        sidoSel.appendChild(option);
    })
});


document.querySelector("#sido").addEventListener("change", function() {
    let sidoSel = document.querySelector("#sido");
    let sidoCode = sidoSel.options[sidoSel.selectedIndex].value;
    
    fetch(`/whereismyhome_be/addr?action=gugun&sido=${sidoCode}`)
        .then(response => response.json())
        .then(data => {
            console.log(data.addrList);
            let gugunSel = document.querySelector("#gugun");
            
            while (gugunSel.firstChild) {
                gugunSel.removeChild(gugunSel.firstChild);
            }
            
            let init = document.createElement("option");
            init.textContent = "구군선택";
            gugunSel.appendChild(init);
            
            data.addrList.forEach(gugun => {
                let option = document.createElement("option");
                option.textContent = gugun.name;
                option.value = gugun.code;
                
                gugunSel.appendChild(option);
            })
        })
});

document.querySelector("#gugun").addEventListener("change", function() {
    let gugunSel = document.querySelector("#gugun");
    let gugunCode = gugunSel.options[gugunSel.selectedIndex].value;
    
    fetch(`/whereismyhome_be/addr?action=dong&gugun=${gugunCode}`)
        .then(response => response.json())
        .then(data => {
            console.log(data.addrList);
            let dongSel = document.querySelector("#dong");
            
            while (dongSel.firstChild) {
                dongSel.removeChild(dongSel.firstChild);
            }
            
            let init = document.createElement("option");
            init.textContent = "동선택";
            dongSel.appendChild(init);
            
            data.addrList.forEach(dong => {
                let option = document.createElement("option");
                option.textContent = dong.name;
                option.value = dong.code;
                
                dongSel.appendChild(option);
            })
        });
});