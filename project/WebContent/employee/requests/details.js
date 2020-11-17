window.onload = () => {
	
	let table = document.querySelector("#contents")
	
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
				
				table.append(row)
			}	
		}
		 
	}
	
	xhr.open('GET', '../servlets/mypending')
	xhr.send()
	
}