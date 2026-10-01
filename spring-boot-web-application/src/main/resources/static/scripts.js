
// load the users

url = "/api/users";

fetch(url)
    .then(response=>response.json())
    .then(users => {

        let html = users.map(user=>`<tr id="tr_${user.id}">
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>${user.active ? "Active" : "Inactive"}</td>
                            <td>
                                <button 
                                    class="btn btn-danger btn-sm"
                                    onclick="onDelete(${user.id})"
                                >Delete</button>
                            </td>
                        </tr>`).join();

        document.querySelector("#tblUsers tbody")
            .innerHTML = html;

    });

function onDelete(id) {

    if (confirm(`Are you sure you want to delete ${id}`)) {

        fetch(`${url}/${id}`, {
            method: 'DELETE'
        }).then(response=>{
            // remove element tr_${id} from the page
            document.getElementById(`tr_${id}`).remove();
        });
    }
}
