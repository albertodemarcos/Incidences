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