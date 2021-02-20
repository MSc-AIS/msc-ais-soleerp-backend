<table class="font-size-10">
    <tbody>
    <tr>
        <td class="bold-all padding-right">Επωνυμία:</td>
        <td>${invoice.customer.name}</td>
    </tr>
    <tr>
        <td class="bold-all padding-right">Επάγγελμα:</td>
        <td>${invoice.customer.professionalActivity}</td>
    </tr>
    <tr>
        <td class="bold-all padding-right">Διεύθυνση:</td>
        <td>${invoice.customer.address.fullAddress}</td>
    </tr>
    <tr>
        <td class="bold-all padding-right">ΑΦΜ:</td>
        <td>${invoice.customer.taxId}</td>
    </tr>
    <tr>
        <td class="bold-all padding-right">ΔΟΥ:</td>
        <td>${invoice.customer.taxOffice}</td>
    </tr>
    </tbody>
</table>