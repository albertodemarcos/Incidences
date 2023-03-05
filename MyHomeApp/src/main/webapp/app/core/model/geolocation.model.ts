export interface IGeolocation {
    latitude?: number;
    longitude?: number;
}

export class Geolocation implements IGeolocation {
    constructor(
        public latitude?: number,
        public longitude?: number,
    ) {}
}