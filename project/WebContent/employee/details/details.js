/**
 * 
 */

function update() {
	let xhr = new XMLHttpRequest()
	
	var data = "username=" + encodeURIComponent(document.querySelector("#username").value)
			+ "&firstName=" + encodeURIComponent(document.querySelector("#firstname").value)
			+ "&lastName=" + encodeURIComponent(document.querySelector("#lastname").value)
			+ "&email=" + encodeURIComponent(document.querySelector("#email").value)
			+ "&isManager=" + encodeURIComponent(document.querySelector("#isManager").value)
			+ "&manager=" + encodeURIComponent(document.querySelector("#manager").value)
	
	let div = document.querySelector("#confirm")
	let confirm = document.createElement("p")
	confirm.setAttribute("class", "confirm")
	confirm.innerText = "Profile successfully updated"
	div.append(confirm)
	
	xhr.open("POST", "../servlets/updateMe", true)
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
	xhr.send(data)
}

window.onload = () => {
	
	let div = document.querySelector("#profile")
	let xhr = new XMLHttpRequest()
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			let user = JSON.parse(xhr.response)
			
			let user_i = document.createElement("input")
			user_i.setAttribute("id", "username")
			user_i.setAttribute("type", "hidden")
			user_i.setAttribute("value", user.username)
			div.append(user_i)
			
			let isManager_i = document.createElement("input")
			isManager_i.setAttribute("id", "isManager")
			isManager_i.setAttribute("type", "hidden")
			isManager_i.setAttribute("value", user.isManager)
			div.append(isManager_i)
			
			let manager_i = document.createElement("input")
			manager_i.setAttribute("id", "manager")
			manager_i.setAttribute("type", "hidden")
			manager_i.setAttribute("value", user.manager)
			div.append(manager_i)
			
			let user_p = document.createElement("p")
			user_p.innerHTML = "<b>Username: </b>" + user.username
			div.append(user_p)
			
			let fn_p = document.createElement("p")
			fn_p.innerHTML = "<b>First Name: </b>"
			let fn_i = document.createElement("input")
			fn_i.setAttribute("id", "firstname")
			fn_i.setAttribute("value", user.firstName)
			fn_p.append(fn_i)
			div.append(fn_p)
			
			let ln_p = document.createElement("p")
			ln_p.innerHTML = "<b>Last Name: </b>"
			let ln_i = document.createElement("input")
			ln_i.setAttribute("id", "lastname")
			ln_i.setAttribute("value", user.lastName)
			ln_p.append(ln_i)
			div.append(ln_p)
			
			let email_p = document.createElement("p")
			email_p.innerHTML = "<b>Email: </b>"
			let email_i = document.createElement("input")
			email_i.setAttribute("id", "email")
			email_i.setAttribute("type", "email")
			email_i.setAttribute("value", user.email)
			email_p.append(email_i)
			div.append(email_p)
			
			let balance_p = document.createElement("p")
			balance_p.innerHTML = "<b>Balance: </b>" + user.balance
			div.append(balance_p)
			
			let manager_p = document.createElement("p")
			manager_p.innerHTML = "<b>Manager: </b>" + user.manager
			div.append(manager_p)
			
			let submit_button = document.createElement("input")
			submit_button.setAttribute("type", "submit")
			submit_button.setAttribute("id", "submit")
			submit_button.setAttribute("onClick", "update();")
			div.append(submit_button)
			
		}
	}
	
	xhr.open("GET", "../servlets/myinfo")
	xhr.send()
	
}