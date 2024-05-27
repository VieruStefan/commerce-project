import secrets
import string
from typing import Optional
import bcrypt
from pydantic import BaseModel, ConfigDict, EmailStr, Field, field_validator
from datetime import datetime


def generate_password():
    """Generates a reasonable password if none is provided."""
    alphanumeric = string.ascii_letters + string.digits
    while True:
        password = "".join(secrets.choice(alphanumeric) for i in range(10))
        if (
            any(c.islower() for c in password)
            and any(c.isupper() for c in password)  # noqa
            and sum(c.isdigit() for c in password) >= 3  # noqa
        ):
            break
    return password

def hash_password(password: str):
    """Generates a hashed version of the provided password."""
    pw = bytes(password, "utf-8")
    salt = bcrypt.gensalt()
    return bcrypt.hashpw(pw, salt)

class UserBase(BaseModel):
    model_config = ConfigDict(from_attributes=True)
    username: str
    email: EmailStr
    
    @field_validator("email")
    def email_required(cls, v):
        if not v:
            raise ValueError("Must not be empty string and must be a email")
        return v

        

class UserLogin(UserBase):
    password: str

    @field_validator("password")
    def password_required(cls, v):
        if not v:
            raise ValueError("Must not be empty string")
        return v
    
class UserRegister(UserLogin):
    password: Optional[str] = Field(None, nullable=True)

    @field_validator("password", mode='before')
    def password_required(cls, v):
        # we generate a password for those that don't have one
        password = v or generate_password()
        return hash_password(password)