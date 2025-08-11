/**
 * IPO Application JavaScript
 */
document.addEventListener('DOMContentLoaded', function() {
    // Set default dates for new IPO form
    const today = new Date();
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);
    
    const formatDate = date => {
        return date.toISOString().split('T')[0];
    };
    
    if (document.getElementById('openDate')) {
        document.getElementById('openDate').min = formatDate(today);
        document.getElementById('openDate').value = formatDate(today);
    }
    
    if (document.getElementById('closeDate')) {
        document.getElementById('closeDate').min = formatDate(tomorrow);
        document.getElementById('closeDate').value = formatDate(tomorrow);
    }
    
    // API base URL
    const API_URL = '/api/ipos';
    
    // Show alert modal
    function showAlert(title, message, isError = false) {
        const alertModal = new bootstrap.Modal(document.getElementById('alertModal'));
        document.getElementById('alertTitle').textContent = title;
        document.getElementById('alertMessage').textContent = message;
        
        if (isError) {
            document.getElementById('alertTitle').classList.add('text-danger');
        } else {
            document.getElementById('alertTitle').classList.remove('text-danger');
        }
        
        alertModal.show();
    }
    
    // Handle API errors
    function handleApiError(error) {
        console.error('API Error:', error);
        showAlert('Error', error.message || 'An unexpected error occurred', true);
    }
    
    // Refresh page
    document.getElementById('refreshBtn').addEventListener('click', function() {
        window.location.reload();
    });
    
    // Create new IPO
    document.getElementById('saveIpoBtn').addEventListener('click', function() {
        const form = document.getElementById('addIpoForm');
        
        const ipoData = {
            companyName: form.companyName.value,
            issuePrice: parseFloat(form.issuePrice.value),
            totalShares: parseInt(form.totalShares.value),
            openDate: form.openDate.value,
            closeDate: form.closeDate.value,
            status: form.status.value,
            description: form.description.value
        };
        
        fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(ipoData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to create IPO');
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                const modal = bootstrap.Modal.getInstance(document.getElementById('addIpoModal'));
                modal.hide();
                showAlert('Success', 'IPO created successfully');
                setTimeout(() => window.location.reload(), 1500);
            } else {
                throw new Error(data.message || 'Failed to create IPO');
            }
        })
        .catch(handleApiError);
    });
    
    // View IPO details
    document.querySelectorAll('.view-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const ipoId = this.getAttribute('data-id');
            
            fetch(`${API_URL}/${ipoId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch IPO details');
                }
                return response.json();
            })
            .then(data => {
                if (data.success && data.data) {
                    const ipo = data.data;
                    const openDate = new Date(ipo.openDate).toLocaleDateString();
                    const closeDate = new Date(ipo.closeDate).toLocaleDateString();
                    
                    let statusClass = '';
                    if (ipo.status === 'UPCOMING') statusClass = 'text-info';
                    else if (ipo.status === 'ACTIVE') statusClass = 'text-success';
                    else statusClass = 'text-secondary';
                    
                    const detailsHtml = `
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">${ipo.companyName}</h4>
                                <p class="card-text ${statusClass}"><strong>Status:</strong> ${ipo.status}</p>
                                <p class="card-text"><strong>Issue Price:</strong> $${ipo.issuePrice}</p>
                                <p class="card-text"><strong>Total Shares:</strong> ${ipo.totalShares.toLocaleString()}</p>
                                <p class="card-text"><strong>Open Date:</strong> ${openDate}</p>
                                <p class="card-text"><strong>Close Date:</strong> ${closeDate}</p>
                                <p class="card-text"><strong>Description:</strong></p>
                                <p class="card-text">${ipo.description || 'No description available'}</p>
                            </div>
                        </div>
                    `;
                    
                    document.getElementById('ipoDetails').innerHTML = detailsHtml;
                    const viewModal = new bootstrap.Modal(document.getElementById('viewIpoModal'));
                    viewModal.show();
                } else {
                    throw new Error(data.message || 'Failed to fetch IPO details');
                }
            })
            .catch(handleApiError);
        });
    });
    
    // Edit IPO
    document.querySelectorAll('.edit-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const ipoId = this.getAttribute('data-id');
            
            fetch(`${API_URL}/${ipoId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch IPO details');
                }
                return response.json();
            })
            .then(data => {
                if (data.success && data.data) {
                    const ipo = data.data;
                    
                    document.getElementById('editIpoId').value = ipo.id;
                    document.getElementById('editCompanyName').value = ipo.companyName;
                    document.getElementById('editIssuePrice').value = ipo.issuePrice;
                    document.getElementById('editTotalShares').value = ipo.totalShares;
                    document.getElementById('editOpenDate').value = ipo.openDate;
                    document.getElementById('editCloseDate').value = ipo.closeDate;
                    document.getElementById('editStatus').value = ipo.status;
                    document.getElementById('editDescription').value = ipo.description || '';
                    
                    const editModal = new bootstrap.Modal(document.getElementById('editIpoModal'));
                    editModal.show();
                } else {
                    throw new Error(data.message || 'Failed to fetch IPO details');
                }
            })
            .catch(handleApiError);
        });
    });
    
    // Update IPO
    document.getElementById('updateIpoBtn').addEventListener('click', function() {
        const form = document.getElementById('editIpoForm');
        const ipoId = document.getElementById('editIpoId').value;
        
        const ipoData = {
            id: ipoId,
            companyName: document.getElementById('editCompanyName').value,
            issuePrice: parseFloat(document.getElementById('editIssuePrice').value),
            totalShares: parseInt(document.getElementById('editTotalShares').value),
            openDate: document.getElementById('editOpenDate').value,
            closeDate: document.getElementById('editCloseDate').value,
            status: document.getElementById('editStatus').value,
            description: document.getElementById('editDescription').value
        };
        
        fetch(`${API_URL}/${ipoId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(ipoData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to update IPO');
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                const modal = bootstrap.Modal.getInstance(document.getElementById('editIpoModal'));
                modal.hide();
                showAlert('Success', 'IPO updated successfully');
                setTimeout(() => window.location.reload(), 1500);
            } else {
                throw new Error(data.message || 'Failed to update IPO');
            }
        })
        .catch(handleApiError);
    });
    
    // Delete IPO
    document.querySelectorAll('.delete-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const ipoId = this.getAttribute('data-id');
            document.getElementById('deleteIpoId').value = ipoId;
            
            const deleteModal = new bootstrap.Modal(document.getElementById('deleteIpoModal'));
            deleteModal.show();
        });
    });
    
    // Confirm delete
    document.getElementById('confirmDeleteBtn').addEventListener('click', function() {
        const ipoId = document.getElementById('deleteIpoId').value;
        
        fetch(`${API_URL}/${ipoId}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to delete IPO');
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                const modal = bootstrap.Modal.getInstance(document.getElementById('deleteIpoModal'));
                modal.hide();
                showAlert('Success', 'IPO deleted successfully');
                setTimeout(() => window.location.reload(), 1500);
            } else {
                throw new Error(data.message || 'Failed to delete IPO');
            }
        })
        .catch(handleApiError);
    });
    
    // Form validation for dates
    document.getElementById('openDate').addEventListener('change', function() {
        const closeDate = document.getElementById('closeDate');
        closeDate.min = this.value;
        if (closeDate.value < this.value) {
            closeDate.value = this.value;
        }
    });
    
    document.getElementById('editOpenDate').addEventListener('change', function() {
        const closeDate = document.getElementById('editCloseDate');
        closeDate.min = this.value;
        if (closeDate.value < this.value) {
            closeDate.value = this.value;
        }
    });
}); 