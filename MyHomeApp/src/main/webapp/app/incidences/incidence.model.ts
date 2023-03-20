import { Organization } from "app/admin/organization-management/organization.model";
import { Geolocation } from "app/core/model/geolocation.model";

  export interface IIncidence {
    id?: number;
    title?: string | null;
    description?: string | null;
    startDate?: Date | null;
    endDate?: Date | null;
    status?: string | null;
    priority?: string | null;
    location?: Geolocation | null;
    organization?: Organization | null;
    organizationId?: number | null;
    /*photos?: string[] | null;*/
    employeeId?: number | null;
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
  }
  
  export class Incidence implements IIncidence {
    constructor(
      public id?: number,
      public title?: string| null,
      public description?: string | null,
      public startDate?: Date| null,
      public endDate?: Date| null,
      public status?: string| null,
      public priority?: string | null,
      public location?: Geolocation| null,
      public organization?: Organization| null,
      public organizationId?: number| null,
      /*public photos?: string[]| null,*/
      public employeeId?: number| null,
      public createdBy?: string,
      public createdDate?: Date,
      public lastModifiedBy?: string,
      public lastModifiedDate?: Date
    ) {}

  }