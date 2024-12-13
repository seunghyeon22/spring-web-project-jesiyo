document.addEventListener('DOMContentLoaded', function () {
    var divide = "{{divide}}";
    var search = "{{search}}";
    var searchType = document.getElementById('searchType');
    var searchInput = document.getElementById('searchInput');
    if (divide) {
        for (var i = 0; i < searchType.options.length; i++) {
            if (searchType.options[i].value === divide) {
                searchType.options[i].selected = true;
                break;
            }
        }
    }
    if (search) {
        searchInput.value = search;
    }
});

function handleSearch(event) {
    event.preventDefault();

    const searchType = document.getElementById('searchType').value;
    const searchInput = document.getElementById('searchInput').value;

    window.location.href = `/auction-progress?divide=${searchType}&search=${searchInput}&page=1`;
}

function changePage(pageNumber) {
    const searchType = document.getElementById('searchType').value;
    const searchInput = document.getElementById('searchInput').value;

    window.location.href = `/auction-progress?divide=${searchType}&search=${searchInput}&page=${pageNumber}`;
}