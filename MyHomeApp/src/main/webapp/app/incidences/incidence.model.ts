export interface IIncidence {
    id: number | null;
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
  }
  
  export class Incidence implements IIncidence {
    constructor(
      public id: number | null,
      public createdBy?: string,
      public createdDate?: Date,
      public lastModifiedBy?: string,
      public lastModifiedDate?: Date
    ) {}
  }