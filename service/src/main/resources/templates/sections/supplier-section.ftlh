<div class="bottom-all width-full">
    <!-- payment section -->
    <#include "payment-section.ftlh">
    <p>
        <table class="width-full grey-text-all upper-border">
            <tbody>
            <tr>
                <td class="font-size-9"><p class="bold-all">Στοιχεία Προμηθευτή</p></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="font-size-9">(Διεύθυνση)</td>
                <td class="font-size-9">ΑΦΜ: ${invoice.supplier.taxId}</td>
                <td class="font-size-9">Bank Name: ${invoice.supplier.bankAccount.bankName}</td>
            </tr>
            <tr>
                <td class="font-size-12">${invoice.supplier.address.street} ${invoice.supplier.address.streetNumber}</td>
                <td class="font-size-9">ΔΟΥ: ${invoice.supplier.taxOffice}</td>
                <td class="font-size-9">Swift Code: ${invoice.supplier.bankAccount.swiftCode}</td>
            </tr>
            <tr>
                <td class="font-size-12">${invoice.supplier.address.postalCode}, ${invoice.supplier.address.city}</td>
                <td class="font-size-9">E-Mail: ${invoice.supplier.representative.email}</td>
                <td class="font-size-9">IBAN: <div class="color-blue font-size-10 bold-all">${invoice.supplier.bankAccount.IBAN}</div></td>
            </tr>
            </tbody>
        </table>

</div>
