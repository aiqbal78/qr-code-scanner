document.getElementById('searchButton').addEventListener('click', function () {
    const qrCode = document.getElementById('qrCodeSearchInput').value;

    if (qrCode) {
        fetch(`/items/search?qrCode=${encodeURIComponent(qrCode)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                // Display the search result
                const searchResultDiv = document.getElementById('searchResult');
                if (data) {
                    searchResultDiv.innerHTML = `
                        <h3>Item Details:</h3>
                        <p>ID: ${data.id}</p>
                        <p>Name: ${data.name}</p>
                        <p>Description: ${data.description}</p>
                        <p>QR Code: <img src="${data.qrCode}" alt="QR Code" width="100"></p>
                    `;
                } else {
                    searchResultDiv.innerHTML = `<p>No item found for QR Code: ${qrCode}</p>`;
                }
            })
            .catch(error => {
                console.error('Error fetching item:', error);
                document.getElementById('searchResult').innerHTML = `<p>Error fetching item details.</p>`;
            });
    } else {
        alert('Please enter a QR code.');
    }
});
