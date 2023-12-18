export interface MyResponse<T> {
    content: T[];
    data: T;
    message: string;
    pageable: any;
    last: boolean;
    totalElements : number;
    totalPages : number;
    size : number;
    number : number;
    sort : any;
    first : boolean;
    numberOfElements : number;
    empty : boolean;
}