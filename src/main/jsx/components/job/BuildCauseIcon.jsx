import React from 'react';
export default React.createClass({
  render(){
    return <i className={"fa  full-width "+ this._resultIcon()}></i>;
  },
  _resultIcon(){
    switch(this.props.cause){
      case "MANUAL": return "fa-user";
      case "GITHUB_PUSH": return "octicon octicon-git-commit";
      case "GITHUB_PULL_REQUEST": return "octicon octicon-git-pull-request";
      case "UPSTREAM": return "fa-arrow-circle-o-up";
      default: return "fa-question-circle";
    }
  }
})
