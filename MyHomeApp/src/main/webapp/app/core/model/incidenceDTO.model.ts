import { Organization } from "app/admin/organization-management/organization.model";

  export interface IIncidenceDTO {
    id?: number;
    title?: string | null;
    description?: string | null;
    startDate?: Date | null;
    endDate?: Date | null;
    status?: string | null;
    priority?: string | null;
    latitude?: number | null;
    longitude?: number | null;
    organization?: Organization | null;
    organizationId?: number | null;
    photosDTO?: [] | null;
    employeeId?: number | null;
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
  }
  
  export class IncidenceDTO implements IIncidenceDTO {
    constructor(
      public id?: number,
      public title?: string| null,
      public description?: string | null,
      public startDate?: Date| null,
      public endDate?: Date| null,
      public status?: string| null,
      public priority?: string | null,
      public latitude?: number | null,
      public longitude?: number | null,
      public organization?: Organization| null,
      public organizationId?: number| null,
      public photosDTO?: []| null,
      public employeeId?: number| null,
      public createdBy?: string,
      public createdDate?: Date,
      public lastModifiedBy?: string,
      public lastModifiedDate?: Date
    ) {}

  }