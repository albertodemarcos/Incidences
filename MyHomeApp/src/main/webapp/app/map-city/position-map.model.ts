
  export interface IPositionMap {
    aNord?: number | undefined,
    aEst?: number | undefined,
    aSud?: number | undefined,
    aOvest?: number | undefined
  }
  
  export class PositionMap implements IPositionMap {
    constructor(
      public aNord?: number,
      public aEst?: number,
      public aSud?: number,
      public aOvest?: number,
    ) {}
  }
