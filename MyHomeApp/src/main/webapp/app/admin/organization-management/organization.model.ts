
import { Geolocation } from "app/core/model/geolocation.model";


export interface IOrganization {
    id?: number;
    name?: string | null;
    description?: string | null;
    type?: string | null;
    activated?: boolean | null,
    geolocation?: Geolocation | null,
    createdBy?: string;
    createdDate?: Date;
    lastModifiedBy?: string;
    lastModifiedDate?: Date;
  }
  
  export class Organization implements IOrganization {
    constructor(
        public id?: number,
        public name?: string | null,
        public description?: string | null,
        public type?: string | null,
        public activated?: boolean | null,
        public geolocation?: Geolocation | null,
        public createdBy?: string,
        public createdDate?: Date,
        public lastModifiedBy?: string,
        public lastModifiedDate?: Date
    ) {}
  }
  