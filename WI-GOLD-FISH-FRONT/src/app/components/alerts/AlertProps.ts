export default class AlertProps {
    visible: boolean = false;
    message: string = '';
    needConfirmation: boolean = false;
    onConfirm?: () => void;
    constructor(
        visible: boolean = false,
        message: string = '',
        needConfirmation: boolean = false,
        onConfirm?: () => void
    ) {
        this.visible = visible;
        this.message = message;
        this.needConfirmation = needConfirmation;
        this.onConfirm = onConfirm;
    }
}