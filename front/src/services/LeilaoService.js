import BaseService from "./BaseServices";

class LeilaoService extends BaseService {
  constructor(parameters) {
    super("/leilao");
  }
}
export default LeilaoService;