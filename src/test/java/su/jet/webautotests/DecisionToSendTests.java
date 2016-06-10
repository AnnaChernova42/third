package su.jet.webautotests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import su.jet.webautotests.Forms.ActionsOverTransactionsForm;
import su.jet.webautotests.Pages.AmlPage;
import su.jet.webautotests.Pages.EditPageTabs.*;
import su.jet.webautotests.Pages.TransactionEditPage;
import su.jet.webautotests.DataConstants.*;
import su.jet.webautotests.utils.TestRailAPI.test.rail.util.TestCase;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.readonly;
import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static su.jet.webautotests.utils.TestLogger.log;

/**
 * Created by ub.kim on 06.04.2016.
 */
@Listeners(su.jet.webautotests.utils.Listener.class)
@Title("Принятие решения на отправку")
public class DecisionToSendTests extends BaseClass {

    AmlPage amlPage = new AmlPage();
    ActionsOverTransactionsForm actionsOverTransactionsForm;
    String transactionId;

    @Test
    @TestCase(testRailCaseId = "34478")
    public void OnSendFromQuickLaunchToolbar(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.BeControlled.toString(), MessageStatus.ControlIsPassed.toString());
        log.info(transactionId);
        amlPage.clickOnToSendBtnFromToolbar()
                .clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.WaitingToBeSent + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.WaitingToBeSent.toString()));
    }

    @Test
    @TestCase(testRailCaseId = "34479")
    public void OnSendFromActionsForm(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.BeControlled.toString(), MessageStatus.ControlIsPassed.toString());
        log.info(transactionId);
        actionsOverTransactionsForm = amlPage.clickOnActionsBtnFromToolbar();
        actionsOverTransactionsForm.actionSelection(SelectAction.TransferToSend.name());
        amlPage.clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.WaitingToBeSent + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.WaitingToBeSent.toString()));
    }

    @Test
    @TestCase(testRailCaseId = "38884")
    public void OnSendUsingQuickLaunchToolbarFromTransactionEditPage(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.BeControlled.toString(), MessageStatus.ControlIsPassed.toString());
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionId);
        editPage.clickOnToSendBtnFromToolbar()
                .exitTransaction();
        amlPage.clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.WaitingToBeSent + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.WaitingToBeSent.toString()));
    }

    @Test
    @TestCase(testRailCaseId = "34480")
    public void OnSendUsingActionsFormFromTransactionEditPage(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.BeControlled.toString(), MessageStatus.ControlIsPassed.toString());
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionId);
        actionsOverTransactionsForm = editPage.clickOnActionsBtnFromTransactionEditPage();
        actionsOverTransactionsForm.actionSelection(SelectAction.TransferToSend.name());
        editPage.exitTransaction();
        amlPage.clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.WaitingToBeSent + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.WaitingToBeSent.toString()));
    }

    @Test
    @TestCase(testRailCaseId = "34481")
    public void ExportToCommitFromQuickLaunchToolbar(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.WaitingToBeSent.toString(), MessageStatus.ControlIsPassed.toString());
        log.info(transactionId);
        amlPage.clickOnExportBtnFromToolbar()
                .clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.Sent + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.Sent.toString()));

        assertThat("Поле \"Статус сообщения\" имеет фактическое значение отличное от ожидаемого (\"" + MessageStatus.Sent + "\")",
                amlPage.getStatusMessage(transactionId), containsString(MessageStatus.Sent.toString()));
    }

    @Test
    @TestCase(testRailCaseId = "34482")
    public void ExportToCommitFromActionsForm(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.WaitingToBeSent.toString(), MessageStatus.ControlIsPassed.toString());
        log.info(transactionId);
        actionsOverTransactionsForm = amlPage.clickOnActionsBtnFromToolbar();
        actionsOverTransactionsForm.actionSelection(SelectAction.SentToCommit.name());
        amlPage.clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.Sent + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.Sent.toString()));

        assertThat("Поле \"Статус сообщения\" имеет фактическое значение отличное от ожидаемого (\"" + MessageStatus.Sent + "\")",
                amlPage.getStatusMessage(transactionId), containsString(MessageStatus.Sent.toString()));
    }

    @Test
    //@TestCase(testRailCaseId = "38903")
    public void ExportToCommitUsingQuickLaunchToolbarFromTransactionEditPage(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.WaitingToBeSent.toString(), MessageStatus.ControlIsPassed.toString());
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionId);
        editPage.clickOnExportBtnFromToolbar()
                .exitTransaction();
        amlPage.clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.Sent + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.Sent.toString()));

        assertThat("Поле \"Статус сообщения\" имеет фактическое значение отличное от ожидаемого (\"" + MessageStatus.Sent + "\")",
                amlPage.getStatusMessage(transactionId), containsString(MessageStatus.Sent.toString()));
    }

    @Test
    @TestCase(testRailCaseId = "34483")
    public void ExportToCommitUsingActionsFormTransactionEditPage(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.WaitingToBeSent.toString(), MessageStatus.ControlIsPassed.toString());
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionId);
        actionsOverTransactionsForm = editPage.clickOnActionsBtnFromTransactionEditPage();
        actionsOverTransactionsForm.actionSelection(SelectAction.SentToCommit.name());
        editPage.exitTransaction();
        amlPage.clearStatusFld()
                .findTransactionByNumber(transactionId);

        assertThat("Поле \"Статус операции\" имеет фактическое значение отличное от ожидаемого (\"" + TransactionStatus.Sent + "\")",
                amlPage.getOperationStatusByTransactionNumber(transactionId), containsString(TransactionStatus.Sent.toString()));

        assertThat("Поле \"Статус сообщения\" имеет фактическое значение отличное от ожидаемого (\"" + MessageStatus.Sent + "\")",
                amlPage.getStatusMessage(transactionId), containsString(MessageStatus.Sent.toString()));
    }

    @Test
    @TestCase(testRailCaseId = "34486")
    public void VerifyThatTransactionEditPageIsReadonlyAfterSent(){
        transactionId = amlPage.selectFirstTransactionByMessageStatus(TransactionStatus.Sent.toString(), MessageStatus.Sent.toString());
        TransactionEditPage editPage = amlPage.openTransactionByDoubleClick(transactionId);

        editPage.openInformationTab();
        /**
         * Блок "Кредитная организация"
         */
        $(OperationInfoTab.creditOrganization).shouldBe(readonly);
        $(OperationInfoTab.creditOrganizationButton).click();
        $(OperationInfoTab.NUMBF_S).shouldBe(readonly);
        $(OperationInfoTab.REGN).shouldBe(readonly);
        $(OperationInfoTab.BIK_S).shouldBe(readonly);
        $(OperationInfoTab.ND_KO).shouldBe(readonly);
        $(OperationInfoTab.KTU_S).shouldBe(readonly);
        $(OperationInfoTab.TEL).shouldBe(readonly);

        /**
         * Блок "Филиал КО"
         */
        $(OperationInfoTab.branchButton).click();
        $(OperationInfoTab.BRANCH).shouldBe(disabled);
        $(OperationInfoTab.NUMBF_SS).shouldBe(readonly);
        $(OperationInfoTab.BIK_SS).shouldBe(readonly);
        $(OperationInfoTab.KTU_SS).shouldBe(readonly);

        /**
         * Блок "Признаки операции"
         */
        $(OperationInfoTab.DATA).shouldBe(readonly);
        $(OperationInfoTab.VO).shouldBe(readonly);
        $(OperationInfoTab.DOP_V).shouldBe(readonly);
        $(OperationInfoTab.PRIZ_SD).shouldBe(readonly);
        $(OperationInfoTab.B_PAYER).shouldBe(readonly);
        $(OperationInfoTab.B_RECIP).shouldBe(readonly);
        $(OperationInfoTab.PART).shouldBe(readonly);
        $(OperationInfoTab.PRIZ6001).shouldBe(readonly);
        $(OperationInfoTab.DATE_S).shouldBe(readonly);
        $(OperationInfoTab.TERROR).shouldBe(readonly);

        /**
         * Блок "Реквизиты платежного документа"
         */
        $(OperationInfoTab.NUM_PAY_D).shouldBe(readonly);
        $(OperationInfoTab.SUME).shouldBe(readonly);
        $(OperationInfoTab.SUM).shouldBe(readonly);
        $(OperationInfoTab.SUM_CON).shouldBe(readonly);
        $(OperationInfoTab.PRIM_1).shouldBe(readonly);
        $(OperationInfoTab.DATE_PAY_D).shouldBe(readonly);
        $(OperationInfoTab.METAL).shouldBe(readonly);
        $(OperationInfoTab.CURREN).shouldBe(readonly);
        $(OperationInfoTab.CURREN_CON).shouldBe(readonly);

        /**
         * Блок "Дополнительная информация о сведениях"
         */
        $(OperationInfoTab.DESCR_1).shouldBe(readonly);
        $(OperationInfoTab.MANUAL_CMNT).shouldBe(readonly);

        editPage.openPayerTab();
        /**
         * Блок "Общие сведения об участнике"
         */
        $(PayerTab.TU0).shouldBe(readonly);
        $(PayerTab.PRU0).shouldBe(readonly);
        $(PayerTab.NAMEU0).shouldBe(readonly);
        $(PayerTab.ND0).shouldBe(readonly);
        $(PayerTab.RG0).shouldBe(readonly);
        $(PayerTab.VP_0).shouldBe(readonly);
        $(PayerTab.CARD_B0).shouldBe(readonly);
        $(PayerTab.GR0).shouldBe(readonly);
        $(PayerTab.BP_0).shouldBe(readonly);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(PayerTab.KODCR0).shouldBe(readonly);
        $(PayerTab.KODCR0_additional).shouldBe(readonly);
        $(PayerTab.AMR_S0).shouldBe(readonly);
        $(PayerTab.AMR_R0).shouldBe(readonly);
        $(PayerTab.AMR_G0).shouldBe(readonly);
        $(PayerTab.AMR_U0).shouldBe(readonly);
        $(PayerTab.AMR_D0).shouldBe(readonly);
        $(PayerTab.AMR_K0).shouldBe(readonly);
        $(PayerTab.AMR_O0).shouldBe(readonly);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(PayerTab.KODCN0).shouldBe(readonly);
        $(PayerTab.KODCN0_additional).shouldBe(readonly);
        $(PayerTab.ADRESS_S0).shouldBe(readonly);
        $(PayerTab.ADRESS_R0).shouldBe(readonly);
        $(PayerTab.ADRESS_G0).shouldBe(readonly);
        $(PayerTab.ADRESS_U0).shouldBe(readonly);
        $(PayerTab.ADRESS_D0).shouldBe(readonly);
        $(PayerTab.ADRESS_K0).shouldBe(readonly);
        $(PayerTab.ADRESS_O0).shouldBe(readonly);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(PayerTab.KD0).shouldBe(readonly);
        $(PayerTab.VD02).shouldBe(readonly);
        $(PayerTab.SD0).shouldBe(readonly);
        $(PayerTab.VD01).shouldBe(readonly);
        $(PayerTab.VD03).shouldBe(readonly);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(PayerTab.VD04).shouldBe(readonly);
        $(PayerTab.VD06).shouldBe(readonly);
        $(PayerTab.VD05).shouldBe(readonly);
        $(PayerTab.VD07).shouldBe(readonly);

        /**
         * Блок "Миграционная карта"
         */
        $(PayerTab.MC_01).shouldBe(readonly);
        $(PayerTab.MC_02).shouldBe(readonly);
        $(PayerTab.MC_03).shouldBe(readonly);

        /**
         * Блок "Банковские идентификационные реквизиты КО, обслуживающей участника"
         */
        $(PayerTab.NAME_B0).shouldBe(readonly);
        $(PayerTab.BIK_B0).shouldBe(readonly);
        $(PayerTab.KODCN_B0).shouldBe(readonly);
        $(PayerTab.KODCN_B0_additional).shouldBe(readonly);
        $(PayerTab.ACC_COR_B0).shouldBe(readonly);
        $(PayerTab.ACC_B0).shouldBe(readonly);

        /**
         * Блок "Банковские идентификационные реквизиты корреспондента"
         */
        $(PayerTab.NAME_R0).shouldBe(readonly);
        $(PayerTab.BIK_R0).shouldBe(readonly);
        $(PayerTab.KODCN_R0).shouldBe(readonly);
        $(PayerTab.KODCN_R0_additional).shouldBe(readonly);

        /**
         * Блок "Данные о банковской карте"
         */
        $(PayerTab.NAME_IS_B0).shouldBe(readonly);
        $(PayerTab.BIK_IS_B0).shouldBe(readonly);
        $(PayerTab.payerComment).shouldBe(readonly);

        editPage.openRecipientTab();
        /**
         * Блок "Общие сведения об участнике"
         */
        $(RecipientTab.TU3).shouldBe(readonly);
        $(RecipientTab.PRU3).shouldBe(readonly);
        $(RecipientTab.NAMEU3).shouldBe(readonly);
        $(RecipientTab.ND3).shouldBe(readonly);
        $(RecipientTab.RG3).shouldBe(readonly);
        $(RecipientTab.VP_3).shouldBe(readonly);
        $(RecipientTab.CARD_B3).shouldBe(readonly);
        $(RecipientTab.GR3).shouldBe(readonly);
        $(RecipientTab.BP_3).shouldBe(readonly);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(RecipientTab.KODCR3).shouldBe(readonly);
        $(RecipientTab.KODCR3_additional).shouldBe(readonly);
        $(RecipientTab.AMR_S3).shouldBe(readonly);
        $(RecipientTab.AMR_R3).shouldBe(readonly);
        $(RecipientTab.AMR_G3).shouldBe(readonly);
        $(RecipientTab.AMR_U3).shouldBe(readonly);
        $(RecipientTab.AMR_D3).shouldBe(readonly);
        $(RecipientTab.AMR_K3).shouldBe(readonly);
        $(RecipientTab.AMR_O3).shouldBe(readonly);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(RecipientTab.KODCN3).shouldBe(readonly);
        $(RecipientTab.KODCN3_additional).shouldBe(readonly);
        $(RecipientTab.ADRESS_S3).shouldBe(readonly);
        $(RecipientTab.ADRESS_R3).shouldBe(readonly);
        $(RecipientTab.ADRESS_G3).shouldBe(readonly);
        $(RecipientTab.ADRESS_U3).shouldBe(readonly);
        $(RecipientTab.ADRESS_D3).shouldBe(readonly);
        $(RecipientTab.ADRESS_K3).shouldBe(readonly);
        $(RecipientTab.ADRESS_O3).shouldBe(readonly);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(RecipientTab.KD3).shouldBe(readonly);
        $(RecipientTab.VD32).shouldBe(readonly);
        $(RecipientTab.SD3).shouldBe(readonly);
        $(RecipientTab.VD31).shouldBe(readonly);
        $(RecipientTab.VD33).shouldBe(readonly);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(RecipientTab.VD34).shouldBe(readonly);
        $(RecipientTab.VD36).shouldBe(readonly);
        $(RecipientTab.VD35).shouldBe(readonly);
        $(RecipientTab.VD37).shouldBe(readonly);

        /**
         * Блок "Миграционная карта"
         */
        $(RecipientTab.MC_31).shouldBe(readonly);
        $(RecipientTab.MC_32).shouldBe(readonly);
        $(RecipientTab.MC_33).shouldBe(readonly);

        /**
         * Блок "Банковские идентификационные реквизиты КО, обслуживающей участника"
         */
        $(RecipientTab.NAME_B3).shouldBe(readonly);
        $(RecipientTab.BIK_B3).shouldBe(readonly);
        $(RecipientTab.KODCN_B3).shouldBe(readonly);
        $(RecipientTab.KODCN_B3_additional).shouldBe(readonly);
        $(RecipientTab.ACC_COR_B3).shouldBe(readonly);
        $(RecipientTab.ACC_B3).shouldBe(readonly);

        /**
         * Блок "Банковские идентификационные реквизиты корреспондента"
         */
        $(RecipientTab.NAME_R3).shouldBe(readonly);
        $(RecipientTab.BIK_R3).shouldBe(readonly);
        $(RecipientTab.KODCN_R3).shouldBe(readonly);
        $(RecipientTab.KODCN_R3_additional).shouldBe(readonly);

        /**
         * Блок "Данные о банковской карте"
         */
        $(RecipientTab.NAME_IS_B3).shouldBe(readonly);
        $(RecipientTab.BIK_IS_B3).shouldBe(readonly);
        $(RecipientTab.recipientComment).shouldBe(readonly);

        editPage.openGuarantorTab();
        /**
         * Блок "Общие сведения об участнике"
         */
        $(GuarantorTab.TU4).shouldBe(readonly);
        $(GuarantorTab.PRU4).shouldBe(readonly);
        $(GuarantorTab.NAMEU4).shouldBe(readonly);
        $(GuarantorTab.ND4).shouldBe(readonly);
        $(GuarantorTab.RG4).shouldBe(readonly);
        $(GuarantorTab.GR4).shouldBe(readonly);
        $(GuarantorTab.BP_4).shouldBe(readonly);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(GuarantorTab.KODCR4).shouldBe(readonly);
        $(GuarantorTab.KODCR4_additional).shouldBe(readonly);
        $(GuarantorTab.AMR_S4).shouldBe(readonly);
        $(GuarantorTab.AMR_R4).shouldBe(readonly);
        $(GuarantorTab.AMR_G4).shouldBe(readonly);
        $(GuarantorTab.AMR_U4).shouldBe(readonly);
        $(GuarantorTab.AMR_D4).shouldBe(readonly);
        $(GuarantorTab.AMR_K4).shouldBe(readonly);
        $(GuarantorTab.AMR_O4).shouldBe(readonly);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(GuarantorTab.KODCN4).shouldBe(readonly);
        $(GuarantorTab.KODCN4_additional).shouldBe(readonly);
        $(GuarantorTab.ADRESS_S4).shouldBe(readonly);
        $(GuarantorTab.ADRESS_R4).shouldBe(readonly);
        $(GuarantorTab.ADRESS_G4).shouldBe(readonly);
        $(GuarantorTab.ADRESS_U4).shouldBe(readonly);
        $(GuarantorTab.ADRESS_D4).shouldBe(readonly);
        $(GuarantorTab.ADRESS_K4).shouldBe(readonly);
        $(GuarantorTab.ADRESS_O4).shouldBe(readonly);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(GuarantorTab.KD4).shouldBe(readonly);
        $(GuarantorTab.VD42).shouldBe(readonly);
        $(GuarantorTab.SD4).shouldBe(readonly);
        $(GuarantorTab.VD41).shouldBe(readonly);
        $(GuarantorTab.VD43).shouldBe(readonly);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(GuarantorTab.VD44).shouldBe(readonly);
        $(GuarantorTab.VD46).shouldBe(readonly);
        $(GuarantorTab.VD45).shouldBe(readonly);
        $(GuarantorTab.VD47).shouldBe(readonly);

        /**
         * Блок "Миграционная карта"
         */
        $(GuarantorTab.MC_41).shouldBe(readonly);
        $(GuarantorTab.MC_42).shouldBe(readonly);
        $(GuarantorTab.MC_43).shouldBe(readonly);
        $(GuarantorTab.guarantorComment).shouldBe(readonly);

        editPage.openPayerAgentTab();
        /**
         * Блок "Общие сведения об участнике"
         */
        $(PayerAgentTab.TU1).shouldBe(readonly);
        $(PayerAgentTab.PRU1).shouldBe(readonly);
        $(PayerAgentTab.NAMEU1).shouldBe(readonly);
        $(PayerAgentTab.ND1).shouldBe(readonly);
        $(PayerAgentTab.RG1).shouldBe(readonly);
        $(PayerAgentTab.GR1).shouldBe(readonly);
        $(PayerAgentTab.BP_1).shouldBe(readonly);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(PayerAgentTab.KODCR1).shouldBe(readonly);
        $(PayerAgentTab.KODCR1_additional).shouldBe(readonly);
        $(PayerAgentTab.AMR_S1).shouldBe(readonly);
        $(PayerAgentTab.AMR_R1).shouldBe(readonly);
        $(PayerAgentTab.AMR_G1).shouldBe(readonly);
        $(PayerAgentTab.AMR_U1).shouldBe(readonly);
        $(PayerAgentTab.AMR_D1).shouldBe(readonly);
        $(PayerAgentTab.AMR_K1).shouldBe(readonly);
        $(PayerAgentTab.AMR_O1).shouldBe(readonly);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(PayerAgentTab.KODCN1).shouldBe(readonly);
        $(PayerAgentTab.KODCN1_additional).shouldBe(readonly);
        $(PayerAgentTab.ADRESS_S1).shouldBe(readonly);
        $(PayerAgentTab.ADRESS_R1).shouldBe(readonly);
        $(PayerAgentTab.ADRESS_G1).shouldBe(readonly);
        $(PayerAgentTab.ADRESS_U1).shouldBe(readonly);
        $(PayerAgentTab.ADRESS_D1).shouldBe(readonly);
        $(PayerAgentTab.ADRESS_K1).shouldBe(readonly);
        $(PayerAgentTab.ADRESS_O1).shouldBe(readonly);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(PayerAgentTab.KD1).shouldBe(readonly);
        $(PayerAgentTab.VD12).shouldBe(readonly);
        $(PayerAgentTab.SD1).shouldBe(readonly);
        $(PayerAgentTab.VD11).shouldBe(readonly);
        $(PayerAgentTab.VD13).shouldBe(readonly);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(PayerAgentTab.VD11).shouldBe(readonly);
        $(PayerAgentTab.VD16).shouldBe(readonly);
        $(PayerAgentTab.VD15).shouldBe(readonly);
        $(PayerAgentTab.VD17).shouldBe(readonly);

        /**
         * Блок "Миграционная карта"
         */
        $(PayerAgentTab.MC_11).shouldBe(readonly);
        $(PayerAgentTab.MC_12).shouldBe(readonly);
        $(PayerAgentTab.MC_13).shouldBe(readonly);
        $(PayerAgentTab.payerAgentComment).shouldBe(readonly);

        editPage.openRecipientAgentTab();
        /**
         * Блок "Общие сведения об участнике"
         */
        $(RecipientAgentTab.TU2).shouldBe(readonly);
        $(RecipientAgentTab.PRU2).shouldBe(readonly);
        $(RecipientAgentTab.NAMEU2).shouldBe(readonly);
        $(RecipientAgentTab.ND2).shouldBe(readonly);
        $(RecipientAgentTab.RG2).shouldBe(readonly);
        $(RecipientAgentTab.GR2).shouldBe(readonly);
        $(RecipientAgentTab.BP_2).shouldBe(readonly);

        /**
         * Блок "Информация о месте регистрации/жительства"
         */
        $(RecipientAgentTab.KODCR2).shouldBe(readonly);
        $(RecipientAgentTab.KODCR2_additional).shouldBe(readonly);
        $(RecipientAgentTab.AMR_S2).shouldBe(readonly);
        $(RecipientAgentTab.AMR_R2).shouldBe(readonly);
        $(RecipientAgentTab.AMR_G2).shouldBe(readonly);
        $(RecipientAgentTab.AMR_U2).shouldBe(readonly);
        $(RecipientAgentTab.AMR_D2).shouldBe(readonly);
        $(RecipientAgentTab.AMR_K2).shouldBe(readonly);
        $(RecipientAgentTab.AMR_O2).shouldBe(readonly);

        /**
         * Блок "Информация о месте нахождения/пребывания"
         */
        $(RecipientAgentTab.KODCN2).shouldBe(readonly);
        $(RecipientAgentTab.KODCN2_additional).shouldBe(readonly);
        $(RecipientAgentTab.ADRESS_S2).shouldBe(readonly);
        $(RecipientAgentTab.ADRESS_R2).shouldBe(readonly);
        $(RecipientAgentTab.ADRESS_G2).shouldBe(readonly);
        $(RecipientAgentTab.ADRESS_U2).shouldBe(readonly);
        $(RecipientAgentTab.ADRESS_D2).shouldBe(readonly);
        $(RecipientAgentTab.ADRESS_K2).shouldBe(readonly);
        $(RecipientAgentTab.ADRESS_O2).shouldBe(readonly);

        /**
         * Блок "Документ, удостоверяющий личность"
         */
        $(RecipientAgentTab.KD2).shouldBe(readonly);
        $(RecipientAgentTab.VD22).shouldBe(readonly);
        $(RecipientAgentTab.SD2).shouldBe(readonly);
        $(RecipientAgentTab.VD21).shouldBe(readonly);
        $(RecipientAgentTab.VD23).shouldBe(readonly);

        /**
         * Блок "Документ, подтверждающий право на пребывание (проживание) в РФ"
         */
        $(RecipientAgentTab.VD22).shouldBe(readonly);
        $(RecipientAgentTab.VD26).shouldBe(readonly);
        $(RecipientAgentTab.VD25).shouldBe(readonly);
        $(RecipientAgentTab.VD27).shouldBe(readonly);

        /**
         * Блок "Миграционная карта"
         */
        $(RecipientAgentTab.MC_21).shouldBe(readonly);
        $(RecipientAgentTab.MC_22).shouldBe(readonly);
        $(RecipientAgentTab.MC_23).shouldBe(readonly);
        $(RecipientAgentTab.recipientAgentComment).shouldBe(readonly);
    }
}
