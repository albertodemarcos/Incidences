
export interface IGeolocation {
    longitude?: number;
    latitude?: number;
}

export class Geolocation implements IGeolocation {
    constructor(
        public longitude?: number,
        public latitude?: number,
    ) {}
}

export interface IOrganization {
    id?: number;
    name?: string | null;
    description?: string | null;
    type?: string | null;
    geolocation?: Geolocation | null;
  }
  
  export class Organization implements IOrganization {
    constructor(
        public id?: number,
        public name?: string | null,
        public description?: string | null,
        public  type?: string | null,
        public geolocation?: Geolocation | null
    ) {}
  }
  