package su.jet.webautotests;

/**
 * Created by ub.kim on 07.04.2016.
 */
public class DataConstants {

    public enum TransactionStatus{

        BeControlled("Подлежит контролю"),
        WaitingToBeSent("Ожидает отправки"),
        Sent("Отправлена"),
        SentReconsideration("!Отправлена (повторное рассмотрение)"),
        Reconsideration("Повторное рассмотрение"),
        AdditionalAnalysis("Доп. анализ");

        private final String name;
        TransactionStatus(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }

    public enum MessageStatus{

        ControlIsPassed("Контроль пройден"),
        Sent("Отправлено");

        private final String name;
        MessageStatus(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }

    public enum SelectAction{

        TransferToSend("Передать на отправку"),
        SentToCommit("Отправить в Комиту"),
        Reconsideration("Повторное рассмотрение");

        private final String name;
        SelectAction(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }

    public enum SelectColumn{

        TransactionNumber("Номер операции"),
        TransactionStatus("Статус операции"),
        TransactionType("Вид операции"),
        AdditionalTransactionType("Доп. вид операции"),
        StatusMessage("Статус сообщения"),
        SumInRub("Сумма в рублях"),
        SumInCurrency("Сумма в валюте"),
        Currency("Валюта"),
        TransactionDate("Дата операции"),
        ProcessTo("Обработать к..."),
        Responsible("Ответственный"),
        Comment("Комментарий"),
        CashOrClearing("Нал/Бнал"),
        Attachment("Приложение");

        private final String name;
        SelectColumn(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }

    public enum SelectContextMenu{

        MessageDetails("Детали сообщения"),
        TransactionDetails("Детали операции"),
        TransactionDetailsOldInterface("Детали операции (старый интерфейс)");

        private final String name;
        SelectContextMenu(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }
}
