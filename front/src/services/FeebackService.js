import BaseService from "./BaseServices";

class FeedbackService extends BaseService {
  constructor(parameters) {
    super("/feedback");
  }
}
export default FeedbackService;