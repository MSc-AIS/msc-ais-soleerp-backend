<table class="width-full collapsed-border margin-top-down font-size-10">
    <tbody>
    <tr>
        <td class="bold-all">Αιτιολογία</td>
        <td class="bold-all center-all">Ώρες</td>
        <td class="bold-all center-all">Τιμή/Ώρα</td>
        <td class="bold-all center-all">ΦΠΑ ${invoice.VATPercentage}</td>
        <td class="bold-all center-all">Ποσό</td>
    </tr>
    <tr>
        <td class="upper-border">${invoice.providedService.descriptionList[0]}</td>
        <td class="center-all upper-border">${invoice.providedService.employmentPeriod.employmentHours}</td>
        <td class="center-all upper-border">&euro; ${invoice.providedService.ratePerHour}</td>
        <td class="center-all upper-border">&euro; ${invoice.VATAmountAsString}</td>
        <td class="right-all upper-border">&euro; ${invoice.totalAmountAsString}</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>${invoice.providedService.descriptionList[1]}</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    </tbody>
</table>