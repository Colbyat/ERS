/**
 * 
 */

window.onload = () => {
	
	let table = document.querySelector("#contents")
	
	let xhr = new XMLHttpRequest()
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			let employees = JSON.parse(xhr.response)
			for(let i = 0; i<employees.length; i++) {
				
				let row = document.createElement("tr")
				
				let username = document.createElement("td")
				username.innerText = employees[i].username
				row.append(username)
				
				let firstName = document.createElement("td")
				firstName.innerText = employees[i].firstName
				row.append(firstName)
				
				let lastName = document.createElement("td")
				lastName.innerText = employees[i].lastName
				row.append(lastName)
				
				let email = document.createElement("td")
				email.innerText = employees[i].email
				row.append(email)
				
				let balance = document.createElement("td")
				balance.innerText = employees[i].balance
				row.append(balance)
				
				let manager = document.createElement("td")
				manager.innerText = employees[i].manager
				row.append(manager)
				
				table.append(row)
			}	
		}
		 
	}
	
	xhr.open('GET', '../servlets/allemployees')
	xhr.send()
	
}