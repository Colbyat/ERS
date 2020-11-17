/**
 * 
 */

function searchUser() {
	let table = document.querySelector("#tb");
	while(table.hasChildNodes()) {
   		table.removeChild(table.firstChild);
	}
	
	let xhr = new XMLHttpRequest()
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			let reimbursements = JSON.parse(xhr.response)
			for(let i = 0; i<reimbursements.length; i++) {
				if(reimbursements[i].submitter==document.getElementById("search").value) {
					let row = document.createElement("tr")
					
					let submitter = document.createElement("td")
					submitter.innerText = reimbursements[i].submitter
					row.append(submitter)
					
					let cost = document.createElement("td")
					cost.innerText = reimbursements[i].cost
					row.append(cost)
					
					let type = document.createElement("td")
					type.innerText = reimbursements[i].type
					row.append(type)
					
					let imageContainer = document.createElement("td")
					let imageURL = document.createElement("img")
					imageURL.setAttribute("src", "/Project_1/image?url="+reimbursements[i].imageURL)
					imageURL.setAttribute("alt", "reciept")
					imageURL.setAttribute("width", 100)
					imageURL.setAttribute("height", 100)
					imageContainer.append(imageURL)
					row.append(imageContainer)
					
					let description = document.createElement("td")
					description.innerText = reimbursements[i].description
					row.append(description)
					
					let gradingFormat = document.createElement("td")
					gradingFormat.innerText = reimbursements[i].gradingFormat
					row.append(gradingFormat)
					
					let buttons = document.createElement("td")
					
					let approveForm = document.createElement("form")
					approveForm.setAttribute("action", "../servlets/approve")
					approveForm.setAttribute("method", "post")
					let approveId = document.createElement("input")
					approveId.setAttribute("type", "hidden")
					approveId.setAttribute("name", "id")
					approveId.setAttribute("value", reimbursements[i].id)
					approveForm.append(approveId)
					let approveButton = document.createElement("input")
					approveButton.setAttribute("type", "submit")
					approveButton.setAttribute("value", "Approve")
					approveForm.append(approveButton)
					buttons.append(approveForm)
					
					let denyForm = document.createElement("form")
					denyForm.setAttribute("action", "../servlets/deny")
					denyForm.setAttribute("method", "post")
					let denyId = document.createElement("input")
					denyId.setAttribute("type", "hidden")
					denyId.setAttribute("name", "id")
					denyId.setAttribute("value", reimbursements[i].id)
					denyForm.append(denyId)
					let denyButton = document.createElement("input")
					denyButton.setAttribute("type", "submit")
					denyButton.setAttribute("value", "Deny")
					denyForm.append(denyButton)
					buttons.append(denyForm)
					
					row.append(buttons)
					
					table.append(row)
				}
			}
		}
	}
	
	
	xhr.open('GET', '../servlets/allpending')
	xhr.send()
}

function findAll() {
	
	let table = document.querySelector("#tb")
	while(table.hasChildNodes()) {
   		table.removeChild(table.firstChild);
	}
	
	let xhr = new XMLHttpRequest()
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			let reimbursements = JSON.parse(xhr.response)
			for(let i = 0; i<reimbursements.length; i++) {
				
				let row = document.createElement("tr")
				
				let submitter = document.createElement("td")
				submitter.innerText = reimbursements[i].submitter
				row.append(submitter)
				
				let cost = document.createElement("td")
				cost.innerText = reimbursements[i].cost
				row.append(cost)
				
				let type = document.createElement("td")
				type.innerText = reimbursements[i].type
				row.append(type)
				
				let imageContainer = document.createElement("td")
				let imageURL = document.createElement("img")
				imageURL.setAttribute("src", "/Project_1/image?url="+reimbursements[i].imageURL)
				imageURL.setAttribute("alt", "reciept")
				imageURL.setAttribute("width", 100)
				imageURL.setAttribute("height", 100)
				imageContainer.append(imageURL)
				row.append(imageContainer)
				
				let description = document.createElement("td")
				description.innerText = reimbursements[i].description
				row.append(description)
				
				let gradingFormat = document.createElement("td")
				gradingFormat.innerText = reimbursements[i].gradingFormat
				row.append(gradingFormat)
				
				let buttons = document.createElement("td")
				
				let approveForm = document.createElement("form")
				approveForm.setAttribute("action", "../servlets/approve")
				approveForm.setAttribute("method", "post")
				let approveId = document.createElement("input")
				approveId.setAttribute("type", "hidden")
				approveId.setAttribute("name", "id")
				approveId.setAttribute("value", reimbursements[i].id)
				approveForm.append(approveId)
				let approveButton = document.createElement("input")
				approveButton.setAttribute("type", "submit")
				approveButton.setAttribute("value", "Approve")
				approveForm.append(approveButton)
				buttons.append(approveForm)
				
				let denyForm = document.createElement("form")
				denyForm.setAttribute("action", "../servlets/deny")
				denyForm.setAttribute("method", "post")
				let denyId = document.createElement("input")
				denyId.setAttribute("type", "hidden")
				denyId.setAttribute("name", "id")
				denyId.setAttribute("value", reimbursements[i].id)
				denyForm.append(denyId)
				let denyButton = document.createElement("input")
				denyButton.setAttribute("type", "submit")
				denyButton.setAttribute("value", "Deny")
				denyForm.append(denyButton)
				buttons.append(denyForm)
				
				row.append(buttons)
				
				table.append(row)
			}	
		}
		 
	}
	
	xhr.open('GET', '../servlets/allpending')
	xhr.send()
	
}

window.onload = () => {
	findAll()
}

