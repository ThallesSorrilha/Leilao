import BaseService from "./BaseServices";

class PessoaService extends BaseService {
  constructor(parameters) {
    super("/pessoa");
  }
}
export default PessoaService;