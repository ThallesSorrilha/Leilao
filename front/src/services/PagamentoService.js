import BaseService from "./BaseServices";

class PagamentoService extends BaseService {
  constructor(parameters) {
    super("/pagamento");
  }
}
export default PagamentoService;