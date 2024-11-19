import { ValidationErrors } from "@angular/forms";

export const getValidatorErrorMessage = (validatorName: string, validatorErrors?: ValidationErrors, fieldName?: String): string|undefined => {
    let args = messages.get(validatorName)?.validatorErrorsKey?.map(name => validatorErrors?.[name]);
    args = args?.filter(value => value !== undefined);
    if(!args)args = [];
    return (args) ? stringFormat(messages.get(validatorName)?.message,fieldName,args) : messages.get(validatorName)?.message;
}

const  messages = new Map<string, {message : string,validatorErrorsKey? : string[]}>([
    ['required',  { message : '{0} is required', validatorErrorsKey :['fieldName']} ],
    ['minlength', { message : '{0} must be at least {1} characters long' ,   validatorErrorsKey :['fieldName','requiredLength']}],
    ['maxlength', { message : '{0} cannot be more than {1} characters long', validatorErrorsKey :['fieldName','requiredLength']}],
    ['email',     { message : 'Email must be a valid email address'}],
    ['pattern',     { message : 'Pattern must be start with http'}],
]);

function stringFormat(template: string|undefined, ...args: any[]) {
    if(template){
        return template.replace(/{(\d+)}/g, (match, index) => {
        return typeof args[index] !== 'undefined' || !Array.isArray(args[index])
            ? args[index]
            : match;
        });
    }
    return undefined;
 }
