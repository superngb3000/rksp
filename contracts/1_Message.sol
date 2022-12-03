// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.7.0 <0.9.0;

contract Message {

    string private message = "Hello World!!!";

    function greet() public view returns (string memory) {
        return message;
    }

    function changeMessage(string memory _message) public {
        message = _message;
    }
}