import { Geolocation } from "app/core/model/geolocation.model";

export interface IGoogleMarkerIncidence {
    id?: number | null,
    title?: string | null,
    location?: Geolocation | null,
  }
  
  export class GoogleMarkerIncidence implements IGoogleMarkerIncidence {
    constructor(
      public id?: number | null,
      public title?: string | null,
      public location?: Geolocation | null,
    ) {}
  }
